package com.example.data.repository

import com.example.data.CivilCodeArticles
import com.example.data.CivilCodeStructure
import com.example.data.CivilQuizLevels
import com.example.data.db.AppDatabase
import com.example.data.db.CivilArticleEntity
import com.example.data.db.FavoriteEntity
import com.example.data.db.NoteEntity
import com.example.data.db.QuizProgressEntity
import com.example.data.db.RecentSearchEntity
import com.example.data.model.CivilArticle
import com.example.data.model.CivilBook
import com.example.data.model.CivilCategory
import com.example.data.model.LatinLegalTerm
import com.example.data.model.LegalQuiz
import com.example.data.model.PrescriptionRule
import com.example.data.model.QuizLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.Normalizer

class CivilCodeRepository(private val database: AppDatabase) {

    private val articleDao = database.articleDao()
    private val favoriteDao = database.favoriteDao()
    private val noteDao = database.noteDao()
    private val recentSearchDao = database.recentSearchDao()
    private val quizProgressDao = database.quizProgressDao()

    init {
        // Asynchronously populate Room Database with Civil Code articles if empty
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (articleDao.getCount() == 0) {
                    val entities = (1..2334).map { id ->
                        CivilCodeArticles.getArticle(id).toEntity()
                    }
                    articleDao.insertArticles(entities)
                }
            } catch (e: Exception) {
                // Room seeding fallback
            }
        }
    }

    private fun CivilArticle.toEntity(): CivilArticleEntity {
        return CivilArticleEntity(
            id = this.id,
            numberFormatted = this.numberFormatted,
            epigraph = this.epigraph,
            bookId = this.bookId,
            bookTitle = this.bookTitle,
            titleName = this.titleName,
            chapterName = this.chapterName,
            sectionName = this.sectionName,
            text = this.text,
            practicalCommentary = this.practicalCommentary,
            keyDoctrineNote = this.keyDoctrineNote,
            categoryId = this.categoryId,
            isKeyArticle = this.isKeyArticle,
            tags = this.tags.joinToString(","),
            relatedArticlesCsv = this.relatedArticles.joinToString(",")
        )
    }

    private fun CivilArticleEntity.toDomain(): CivilArticle {
        val tagList = if (this.tags.isBlank()) emptyList() else this.tags.split(",")
        val related = if (this.relatedArticlesCsv.isBlank()) emptyList() else this.relatedArticlesCsv.split(",").mapNotNull { it.toIntOrNull() }
        return CivilArticle(
            id = this.id,
            numberFormatted = this.numberFormatted,
            epigraph = this.epigraph,
            bookId = this.bookId,
            bookTitle = this.bookTitle,
            titleName = this.titleName,
            chapterName = this.chapterName,
            sectionName = this.sectionName,
            text = this.text,
            practicalCommentary = this.practicalCommentary,
            keyDoctrineNote = this.keyDoctrineNote,
            relatedArticles = related,
            categoryId = this.categoryId,
            isKeyArticle = this.isKeyArticle,
            tags = tagList
        )
    }

    fun getBooks(): List<CivilBook> = CivilCodeStructure.BOOKS

    fun getBookById(id: String): CivilBook? = CivilCodeStructure.BOOKS.find { it.id == id }

    fun getCategories(): List<CivilCategory> = CivilCodeStructure.CATEGORIES

    fun getCategoryById(id: String): CivilCategory? = CivilCodeStructure.CATEGORIES.find { it.id == id }

    fun getArticle(articleId: Int): CivilArticle {
        val clampedId = articleId.coerceIn(1, 2334)
        return CivilCodeArticles.getArticle(clampedId)
    }

    fun getArticlesForBook(bookId: String, page: Int = 1, pageSize: Int = 100): List<CivilArticle> {
        val book = getBookById(bookId) ?: return emptyList()
        val start = book.articleRangeStart + (page - 1) * pageSize
        val end = minOf(start + pageSize - 1, book.articleRangeEnd)
        if (start > book.articleRangeEnd) return emptyList()

        return (start..end).map { CivilCodeArticles.getArticle(it) }
    }

    fun getArticlesForCategory(categoryId: String): List<CivilArticle> {
        val cat = getCategoryById(categoryId) ?: return emptyList()
        return cat.highlightedArticleIds.map { CivilCodeArticles.getArticle(it) }
    }

    fun getKeyArticles(): List<CivilArticle> {
        val keyIds = listOf(1, 5, 9, 66, 70, 138, 217, 227, 280, 286, 309, 310, 334, 406, 483, 496, 503, 562, 762, 874, 875, 1022, 1207, 1251, 1287, 1305, 1414, 1424, 1576, 1717, 1721, 1773, 2003, 2024, 2133, 2156, 2179)
        return keyIds.map { CivilCodeArticles.getArticle(it) }
    }

    fun getPersistedArticles(): Flow<List<CivilArticle>> {
        return articleDao.getAllArticles().map { list ->
            list.map { it.toDomain() }
        }
    }

    suspend fun searchArticles(
        query: String,
        bookFilter: String? = null,
        categoryFilter: String? = null
    ): List<CivilArticle> = withContext(Dispatchers.Default) {
        val cleanQuery = query.trim()
        if (cleanQuery.isEmpty()) return@withContext emptyList()

        // Check if user is searching by exact article number, e.g., "483", "art 483", "artigo 483"
        val numberRegex = Regex("""(?i)(?:art(?:igo)?\.?\s*)?(\d{1,4})""")
        val numberMatch = numberRegex.matchEntire(cleanQuery)
        val directNumber = numberMatch?.groupValues?.get(1)?.toIntOrNull()

        if (directNumber != null && directNumber in 1..2334) {
            val article = getArticle(directNumber)
            return@withContext listOf(article)
        }

        val normalizedQuery = normalizeString(cleanQuery)
        val queryTokens = normalizedQuery.split(" ").filter { it.isNotBlank() }

        val allArticles = (1..2334).map { CivilCodeArticles.getArticle(it) }

        val filtered = allArticles.filter { article ->
            val bookMatches = bookFilter == null || article.bookId == bookFilter
            val categoryMatches = categoryFilter == null || article.categoryId == categoryFilter

            if (!bookMatches || !categoryMatches) return@filter false

            val normTitle = normalizeString(article.epigraph)
            val normText = normalizeString(article.text)
            val normCommentary = normalizeString(article.practicalCommentary)
            val normNumber = normalizeString(article.numberFormatted)
            val normTags = normalizeString(article.tags.joinToString(" "))

            queryTokens.all { token ->
                normNumber.contains(token) ||
                normTitle.contains(token) ||
                normText.contains(token) ||
                normCommentary.contains(token) ||
                normTags.contains(token)
            }
        }

        // Rank results: exact epigraph match > starts with > text matches
        return@withContext filtered.sortedByDescending { article ->
            var score = 0
            val normTitle = normalizeString(article.epigraph)
            if (normTitle.contains(normalizedQuery)) score += 50
            if (article.isKeyArticle) score += 20
            if (article.text.contains(cleanQuery, ignoreCase = true)) score += 10
            score
        }.take(80)
    }

    private fun normalizeString(text: String): String {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
            .lowercase()
    }

    // Room Favorites
    fun getAllFavorites(): Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorites()

    fun isFavorite(articleId: Int): Flow<Boolean> = favoriteDao.isFavorite(articleId)

    suspend fun toggleFavorite(articleId: Int, folderTag: String = "Geral") = withContext(Dispatchers.IO) {
        val existing = favoriteDao.getFavoriteByArticleId(articleId).firstOrNull()
        if (existing != null) {
            favoriteDao.deleteFavorite(articleId)
        } else {
            favoriteDao.insertFavorite(FavoriteEntity(articleId = articleId, folderTag = folderTag))
        }
    }

    suspend fun setFavoriteTag(articleId: Int, newTag: String) = withContext(Dispatchers.IO) {
        favoriteDao.insertFavorite(FavoriteEntity(articleId = articleId, folderTag = newTag))
    }

    // Room Notes
    fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()

    fun getNotesForArticle(articleId: Int): Flow<List<NoteEntity>> = noteDao.getNotesForArticle(articleId)

    suspend fun saveNote(
        articleId: Int,
        title: String,
        content: String,
        colorTag: String = "Azul",
        id: Long = 0
    ) = withContext(Dispatchers.IO) {
        if (id == 0L) {
            noteDao.insertNote(
                NoteEntity(
                    articleId = articleId,
                    title = title,
                    content = content,
                    colorTag = colorTag
                )
            )
        } else {
            noteDao.updateNote(
                NoteEntity(
                    id = id,
                    articleId = articleId,
                    title = title,
                    content = content,
                    colorTag = colorTag,
                    updatedAt = System.currentTimeMillis()
                )
            )
        }
    }

    suspend fun deleteNote(noteId: Long) = withContext(Dispatchers.IO) {
        noteDao.deleteNoteById(noteId)
    }

    // Recent Searches
    fun getRecentSearches(): Flow<List<String>> = recentSearchDao.getRecentSearches()

    suspend fun saveSearch(query: String) = withContext(Dispatchers.IO) {
        if (query.isNotBlank()) {
            recentSearchDao.insertSearch(RecentSearchEntity(query.trim()))
        }
    }

    suspend fun deleteSearch(query: String) = withContext(Dispatchers.IO) {
        recentSearchDao.deleteSearch(query)
    }

    suspend fun clearRecentSearches() = withContext(Dispatchers.IO) {
        recentSearchDao.clearAllSearches()
    }

    // Study & Tools
    fun getLatinTerms(): List<LatinLegalTerm> = CivilCodeStructure.LATIN_TERMS

    fun getPrescriptionRules(): List<PrescriptionRule> = CivilCodeStructure.PRESCRIPTION_RULES

    fun getQuizQuestions(): List<LegalQuiz> = CivilCodeStructure.QUIZ_QUESTIONS

    // Quiz Levels & Progression
    fun getQuizLevels(): List<QuizLevel> = CivilQuizLevels.LEVELS

    fun getQuizLevelById(levelId: Int): QuizLevel? = CivilQuizLevels.LEVELS.find { it.levelId == levelId }

    fun getAllQuizProgress(): Flow<List<QuizProgressEntity>> = quizProgressDao.getAllProgress()

    suspend fun recordQuizAttempt(levelId: Int, score: Int): QuizProgressEntity = withContext(Dispatchers.IO) {
        val existing = quizProgressDao.getProgressForLevel(levelId)
        val attempts = (existing?.attemptsCount ?: 0) + 1
        val newBest = maxOf(existing?.bestScore ?: 0, score)
        // Success condition: fail at most 3 in 10 -> at least 7/10
        val isCompleted = newBest >= 7 || (existing?.isCompleted == true)
        val isPerfect = newBest == 10 || (existing?.isPerfect == true)
        // Scoring: 1 point per correct answer + 3 extra bonus points if 100% (10/10)
        val earnedPoints = newBest + (if (isPerfect) 3 else 0)

        val updated = QuizProgressEntity(
            levelId = levelId,
            isCompleted = isCompleted,
            bestScore = newBest,
            attemptsCount = attempts,
            isPerfect = isPerfect,
            earnedPoints = earnedPoints,
            lastPlayedTimestamp = System.currentTimeMillis()
        )
        quizProgressDao.saveProgress(updated)
        updated
    }
}
