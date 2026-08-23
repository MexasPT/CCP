package com.example.data.model

data class CivilBook(
    val id: String, // PARTE_GERAL, LIVRO_II, LIVRO_III, LIVRO_IV, LIVRO_V
    val romanNumber: String, // I, II, III, IV, V
    val title: String, // "PARTE GERAL", "DIREITO DAS OBRIGAÇÕES", etc.
    val subtitle: String,
    val articleRangeStart: Int,
    val articleRangeEnd: Int,
    val totalArticles: Int,
    val description: String,
    val colorHex: Long,
    val keyTopics: List<String>
)

data class CivilCategory(
    val id: String,
    val name: String,
    val subtitle: String,
    val iconName: String,
    val bookId: String,
    val colorHex: Long,
    val articleRangeDescription: String,
    val highlightedArticleIds: List<Int>,
    val description: String
)

data class CivilArticle(
    val id: Int, // 1 to 2334
    val numberFormatted: String, // "Artigo 1.º", "Artigo 483.º"
    val epigraph: String, // "Princípio geral", "Fontes do direito", etc.
    val bookId: String,
    val bookTitle: String,
    val titleName: String,
    val chapterName: String,
    val sectionName: String = "",
    val text: String,
    val practicalCommentary: String = "",
    val keyDoctrineNote: String = "",
    val relatedArticles: List<Int> = emptyList(),
    val categoryId: String = "",
    val isKeyArticle: Boolean = false,
    val tags: List<String> = emptyList()
)

data class LatinLegalTerm(
    val term: String,
    val translation: String,
    val explanation: String,
    val articleRef: Int
)

data class LegalQuiz(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val articleId: Int,
    val category: String
)

data class PrescriptionRule(
    val title: String,
    val durationText: String,
    val durationYears: Float,
    val articleId: Int,
    val description: String,
    val examples: List<String>
)
