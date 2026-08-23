package com.example.ui.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.db.FavoriteEntity
import com.example.data.db.NoteEntity
import com.example.data.model.CivilArticle
import com.example.data.model.CivilBook
import com.example.data.model.CivilCategory
import com.example.data.model.LatinLegalTerm
import com.example.data.model.LegalQuiz
import com.example.data.model.PrescriptionRule
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
    val activeQuizIndex: Int = 0,
    val quizSelectedOption: Int? = null,
    val quizScore: Int = 0,
    val isQuizAnswerSubmitted: Boolean = false
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
    val quizQuestions: List<LegalQuiz> = repository.getQuizQuestions()

    val favorites: StateFlow<List<FavoriteEntity>> = repository.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allNotes: StateFlow<List<NoteEntity>> = repository.getAllNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val recentSearches: StateFlow<List<String>> = repository.getRecentSearches()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("pt", "PT"))
            ttsReady = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED
            if (!ttsReady) {
                // Fallback to Brazilian Portuguese or default Portuguese if PT-PT voice is not present
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

    // Search Engine
    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        performSearch()
    }

    fun setSearchFilters(bookId: String?, categoryId: String?) {
        _uiState.value = _uiState.value.copy(
            searchBookFilter = bookId,
            searchCategoryFilter = categoryId
        )
        performSearch()
    }

    fun performSearch() {
        val q = _uiState.value.searchQuery
        if (q.isBlank()) {
            _uiState.value = _uiState.value.copy(searchResults = emptyList(), isSearching = false)
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSearching = true)
            val results = repository.searchArticles(
                query = q,
                bookFilter = _uiState.value.searchBookFilter,
                categoryFilter = _uiState.value.searchCategoryFilter
            )
            _uiState.value = _uiState.value.copy(
                searchResults = results,
                isSearching = false
            )
            if (q.length > 2) {
                repository.saveSearch(q)
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
    fun toggleFavorite(articleId: Int, tag: String = "Geral") {
        viewModelScope.launch {
            repository.toggleFavorite(articleId, tag)
        }
    }

    fun setFavoriteTag(articleId: Int, tag: String) {
        viewModelScope.launch {
            repository.setFavoriteTag(articleId, tag)
        }
    }

    fun isArticleFavorite(articleId: Int): Boolean {
        return favorites.value.any { it.articleId == articleId }
    }

    // Notes
    fun saveNote(articleId: Int, title: String, content: String, colorTag: String, noteId: Long = 0) {
        viewModelScope.launch {
            repository.saveNote(articleId, title, content, colorTag, noteId)
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    // Export Studio Selection
    fun toggleExportSelection(articleId: Int) {
        val current = _uiState.value.selectedForExport.toMutableSet()
        if (current.contains(articleId)) {
            current.remove(articleId)
        } else {
            current.add(articleId)
        }
        _uiState.value = _uiState.value.copy(selectedForExport = current)
    }

    fun selectAllInCurrentView(articleIds: List<Int>) {
        val current = _uiState.value.selectedForExport.toMutableSet()
        current.addAll(articleIds)
        _uiState.value = _uiState.value.copy(selectedForExport = current)
    }

    fun clearExportSelection() {
        _uiState.value = _uiState.value.copy(selectedForExport = emptySet())
    }

    fun getSelectedArticlesForExport(): List<CivilArticle> {
        return _uiState.value.selectedForExport.map { repository.getArticle(it) }.sortedBy { it.id }
    }

    // Reader Customization
    fun changeFontSize(delta: Float) {
        val newScale = (_uiState.value.textScale + delta).coerceIn(0.8f, 1.6f)
        _uiState.value = _uiState.value.copy(textScale = newScale)
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

    // Quiz Mode
    fun selectQuizOption(index: Int) {
        if (_uiState.value.isQuizAnswerSubmitted) return
        _uiState.value = _uiState.value.copy(quizSelectedOption = index)
    }

    fun submitQuizAnswer() {
        val currentQ = quizQuestions[_uiState.value.activeQuizIndex]
        val isCorrect = _uiState.value.quizSelectedOption == currentQ.correctIndex
        val newScore = if (isCorrect) _uiState.value.quizScore + 1 else _uiState.value.quizScore
        _uiState.value = _uiState.value.copy(
            isQuizAnswerSubmitted = true,
            quizScore = newScore
        )
    }

    fun nextQuizQuestion() {
        val nextIdx = (_uiState.value.activeQuizIndex + 1) % quizQuestions.size
        _uiState.value = _uiState.value.copy(
            activeQuizIndex = nextIdx,
            quizSelectedOption = null,
            isQuizAnswerSubmitted = false
        )
    }

    fun resetQuiz() {
        _uiState.value = _uiState.value.copy(
            activeQuizIndex = 0,
            quizSelectedOption = null,
            isQuizAnswerSubmitted = false,
            quizScore = 0
        )
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
