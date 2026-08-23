package com.example.ui.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.db.FavoriteEntity
import com.example.data.db.NoteEntity
import com.example.data.db.QuizProgressEntity
import com.example.data.model.CivilArticle
import com.example.data.model.CivilBook
import com.example.data.model.CivilCategory
import com.example.data.model.LatinLegalTerm
import com.example.data.model.LegalQuiz
import com.example.data.model.PrescriptionRule
import com.example.data.model.QuizLevel
import com.example.data.repository.CivilCodeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale

enum class AppScreen {
    HOME,
    BOOK_EXPLORER,
    ARTICLE_DETAIL,
    ADVANCED_SEARCH,
    FAVORITES_AND_NOTES,
    EXPORT_STUDIO,
    STUDY_TOOLS
}

data class UiState(
    val currentScreen: AppScreen = AppScreen.HOME,
    val previousScreens: List<AppScreen> = emptyList(),
    val selectedArticleId: Int = 1,
    val selectedBookId: String = "PARTE_GERAL",
    val selectedCategoryId: String? = null,
    val searchQuery: String = "",
    val searchBookFilter: String? = null,
    val searchCategoryFilter: String? = null,
    val searchResults: List<CivilArticle> = emptyList(),
    val isSearching: Boolean = false,
    val selectedForExport: Set<Int> = emptySet(),
    val textScale: Float = 1.0f,
    val useSerifFont: Boolean = true,
    val isTtsSpeaking: Boolean = false,
    
    // Gamified Level Quiz State
    val selectedQuizLevelId: Int = 1,
    val isPlayingQuizLevel: Boolean = false,
    val levelQuestionIndex: Int = 0,
    val levelSelectedOption: Int? = null,
    val isLevelAnswerSubmitted: Boolean = false,
    val currentLevelAttemptScore: Int = 0,
    val isLevelFinished: Boolean = false
)

class CivilCodeViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val repository: CivilCodeRepository
    private var tts: TextToSpeech? = null
    private var ttsReady = false

    init {
        val db = AppDatabase.getDatabase(application)
        repository = CivilCodeRepository(db)
        tts = TextToSpeech(application, this)
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val books: List<CivilBook> = repository.getBooks()
    val categories: List<CivilCategory> = repository.getCategories()
    val keyArticles: List<CivilArticle> = repository.getKeyArticles()
    val latinTerms: List<LatinLegalTerm> = repository.getLatinTerms()
    val prescriptionRules: List<PrescriptionRule> = repository.getPrescriptionRules()
    val quizLevels: List<QuizLevel> = repository.getQuizLevels()

    val favorites: StateFlow<List<FavoriteEntity>> = repository.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allNotes: StateFlow<List<NoteEntity>> = repository.getAllNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val recentSearches: StateFlow<List<String>> = repository.getRecentSearches()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val quizProgressList: StateFlow<List<QuizProgressEntity>> = repository.getAllQuizProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("pt", "PT"))
            ttsReady = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED
            if (!ttsReady) {
                val resultBr = tts?.setLanguage(Locale("pt", "BR"))
                ttsReady = resultBr != TextToSpeech.LANG_MISSING_DATA && resultBr != TextToSpeech.LANG_NOT_SUPPORTED
            }
        }
    }

    fun navigateTo(screen: AppScreen) {
        val current = _uiState.value.currentScreen
        if (current == screen) return
        _uiState.value = _uiState.value.copy(
            currentScreen = screen,
            previousScreens = _uiState.value.previousScreens + current
        )
    }

    fun navigateBack(): Boolean {
        if (_uiState.value.currentScreen == AppScreen.STUDY_TOOLS && _uiState.value.isPlayingQuizLevel) {
            exitLevelToLevelList()
            return true
        }

        val history = _uiState.value.previousScreens
        if (history.isNotEmpty()) {
            val target = history.last()
            _uiState.value = _uiState.value.copy(
                currentScreen = target,
                previousScreens = history.dropLast(1)
            )
            stopTts()
            return true
        }
        return false
    }

    fun openArticle(articleId: Int) {
        val validId = articleId.coerceIn(1, 2334)
        stopTts()
        val current = _uiState.value.currentScreen
        _uiState.value = _uiState.value.copy(
            selectedArticleId = validId,
            currentScreen = AppScreen.ARTICLE_DETAIL,
            previousScreens = if (current != AppScreen.ARTICLE_DETAIL) _uiState.value.previousScreens + current else _uiState.value.previousScreens
        )
    }

    fun selectBook(bookId: String) {
        _uiState.value = _uiState.value.copy(
            selectedBookId = bookId,
            selectedCategoryId = null,
            currentScreen = AppScreen.BOOK_EXPLORER
        )
    }

    fun selectCategory(categoryId: String) {
        val cat = repository.getCategoryById(categoryId)
        _uiState.value = _uiState.value.copy(
            selectedCategoryId = categoryId,
            selectedBookId = cat?.bookId ?: "PARTE_GERAL",
            currentScreen = AppScreen.BOOK_EXPLORER
        )
    }

    fun getArticle(articleId: Int): CivilArticle {
        return repository.getArticle(articleId)
    }

    fun getArticlesForCurrentBook(): List<CivilArticle> {
        val catId = _uiState.value.selectedCategoryId
        return if (catId != null) {
            repository.getArticlesForCategory(catId)
        } else {
            repository.getArticlesForBook(_uiState.value.selectedBookId, page = 1, pageSize = 80)
        }
    }

    fun nextArticle() {
        val nextId = (_uiState.value.selectedArticleId + 1).coerceAtMost(2334)
        openArticle(nextId)
    }

    fun previousArticle() {
        val prevId = (_uiState.value.selectedArticleId - 1).coerceAtLeast(1)
        openArticle(prevId)
    }

    // Search
    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        performSearch(query, _uiState.value.searchBookFilter, _uiState.value.searchCategoryFilter)
    }

    fun setSearchFilters(bookFilter: String?, categoryFilter: String?) {
        _uiState.value = _uiState.value.copy(
            searchBookFilter = bookFilter,
            searchCategoryFilter = categoryFilter
        )
        performSearch(_uiState.value.searchQuery, bookFilter, categoryFilter)
    }

    private fun performSearch(query: String, bookFilter: String?, categoryFilter: String?) {
        viewModelScope.launch {
            if (query.isBlank()) {
                _uiState.value = _uiState.value.copy(searchResults = emptyList(), isSearching = false)
                return@launch
            }
            _uiState.value = _uiState.value.copy(isSearching = true)
            val results = repository.searchArticles(query, bookFilter, categoryFilter)
            _uiState.value = _uiState.value.copy(searchResults = results, isSearching = false)
            if (query.length >= 3) {
                repository.saveSearch(query)
            }
        }
    }

    fun deleteRecentSearch(query: String) {
        viewModelScope.launch {
            repository.deleteSearch(query)
        }
    }

    fun clearRecentSearches() {
        viewModelScope.launch {
            repository.clearRecentSearches()
        }
    }

    // Favorites
    fun isArticleFavorite(articleId: Int): Boolean {
        return favorites.value.any { it.articleId == articleId }
    }

    fun toggleFavorite(articleId: Int, folderTag: String = "Geral") {
        viewModelScope.launch {
            repository.toggleFavorite(articleId, folderTag)
        }
    }

    fun updateFavoriteTag(articleId: Int, tag: String) {
        viewModelScope.launch {
            repository.setFavoriteTag(articleId, tag)
        }
    }

    // Notes
    fun saveNote(articleId: Int, title: String, content: String, colorTag: String = "Azul", noteId: Long = 0) {
        viewModelScope.launch {
            repository.saveNote(articleId, title, content, colorTag, noteId)
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    // Export Selection
    fun toggleExportSelection(articleId: Int) {
        val current = _uiState.value.selectedForExport.toMutableSet()
        if (current.contains(articleId)) {
            current.remove(articleId)
        } else {
            current.add(articleId)
        }
        _uiState.value = _uiState.value.copy(selectedForExport = current)
    }

    fun selectAllForExport(articleIds: List<Int>) {
        _uiState.value = _uiState.value.copy(selectedForExport = articleIds.toSet())
    }

    fun selectAllInCurrentView(articleIds: List<Int>) {
        val current = _uiState.value.selectedForExport.toMutableSet()
        if (current.containsAll(articleIds)) {
            current.removeAll(articleIds.toSet())
        } else {
            current.addAll(articleIds)
        }
        _uiState.value = _uiState.value.copy(selectedForExport = current)
    }

    fun getSelectedArticlesForExport(): List<CivilArticle> {
        return _uiState.value.selectedForExport.sorted().map { repository.getArticle(it) }
    }

    fun clearExportSelection() {
        _uiState.value = _uiState.value.copy(selectedForExport = emptySet())
    }

    fun adjustTextScale(delta: Float) {
        val newScale = (_uiState.value.textScale + delta).coerceIn(0.8f, 1.6f)
        _uiState.value = _uiState.value.copy(textScale = newScale)
    }

    fun changeFontSize(delta: Float) {
        adjustTextScale(delta)
    }

    fun changeFontSize(increase: Boolean) {
        val delta = if (increase) 0.1f else -0.1f
        adjustTextScale(delta)
    }

    fun toggleFontFamily() {
        _uiState.value = _uiState.value.copy(useSerifFont = !_uiState.value.useSerifFont)
    }

    // Text to Speech
    fun speakArticle(article: CivilArticle) {
        if (!ttsReady || tts == null) return
        val toSpeak = "${article.numberFormatted}. ${article.epigraph}. ${article.text}"
        tts?.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, "ARTICLE_TTS")
        _uiState.value = _uiState.value.copy(isTtsSpeaking = true)
    }

    fun stopTts() {
        if (tts != null) {
            tts?.stop()
            _uiState.value = _uiState.value.copy(isTtsSpeaking = false)
        }
    }

    // Gamified Level Quiz System
    fun isLevelUnlocked(levelId: Int, progressList: List<QuizProgressEntity>): Boolean {
        if (levelId <= 1) return true
        val prevProgress = progressList.find { it.levelId == levelId - 1 }
        return prevProgress?.isCompleted == true || (prevProgress?.bestScore ?: 0) >= 7
    }

    fun startQuizLevel(levelId: Int) {
        _uiState.value = _uiState.value.copy(
            selectedQuizLevelId = levelId,
            isPlayingQuizLevel = true,
            levelQuestionIndex = 0,
            levelSelectedOption = null,
            isLevelAnswerSubmitted = false,
            currentLevelAttemptScore = 0,
            isLevelFinished = false
        )
    }

    fun selectQuizOption(index: Int) {
        if (_uiState.value.isLevelAnswerSubmitted) return
        _uiState.value = _uiState.value.copy(levelSelectedOption = index)
    }

    fun submitQuizAnswer() {
        val level = quizLevels.find { it.levelId == _uiState.value.selectedQuizLevelId } ?: return
        val currentQ = level.questions.getOrNull(_uiState.value.levelQuestionIndex) ?: return
        val isCorrect = _uiState.value.levelSelectedOption == currentQ.correctIndex
        val newScore = if (isCorrect) _uiState.value.currentLevelAttemptScore + 1 else _uiState.value.currentLevelAttemptScore

        _uiState.value = _uiState.value.copy(
            isLevelAnswerSubmitted = true,
            currentLevelAttemptScore = newScore
        )
    }

    fun nextQuizQuestion() {
        val level = quizLevels.find { it.levelId == _uiState.value.selectedQuizLevelId } ?: return
        val nextIdx = _uiState.value.levelQuestionIndex + 1

        if (nextIdx < level.questions.size) {
            _uiState.value = _uiState.value.copy(
                levelQuestionIndex = nextIdx,
                levelSelectedOption = null,
                isLevelAnswerSubmitted = false
            )
        } else {
            val finalScore = _uiState.value.currentLevelAttemptScore
            _uiState.value = _uiState.value.copy(
                isLevelFinished = true
            )
            viewModelScope.launch {
                repository.recordQuizAttempt(level.levelId, finalScore)
            }
        }
    }

    fun retryCurrentLevel() {
        startQuizLevel(_uiState.value.selectedQuizLevelId)
    }

    fun nextQuizLevel() {
        val nextLevelId = _uiState.value.selectedQuizLevelId + 1
        if (nextLevelId <= quizLevels.size) {
            startQuizLevel(nextLevelId)
        } else {
            exitLevelToLevelList()
        }
    }

    fun exitLevelToLevelList() {
        _uiState.value = _uiState.value.copy(
            isPlayingQuizLevel = false,
            isLevelFinished = false,
            levelQuestionIndex = 0,
            levelSelectedOption = null,
            isLevelAnswerSubmitted = false
        )
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
