package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "civil_articles")
data class CivilArticleEntity(
    @PrimaryKey val id: Int, // 1 to 2334
    val numberFormatted: String, // "Artigo 1.º", "Artigo 483.º"
    val epigraph: String,
    val bookId: String,
    val bookTitle: String,
    val titleName: String,
    val chapterName: String,
    val sectionName: String = "",
    val text: String,
    val practicalCommentary: String = "",
    val keyDoctrineNote: String = "",
    val categoryId: String = "",
    val isKeyArticle: Boolean = false,
    val tags: String = "", // Comma-separated
    val relatedArticlesCsv: String = "" // Comma-separated IDs
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val articleId: Int,
    val folderTag: String = "Geral",
    val addedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "article_notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val articleId: Int,
    val title: String,
    val content: String,
    val colorTag: String = "Azul", // Azul, Dourado, Verde, Vinho, Roxo
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "recent_searches")
data class RecentSearchEntity(
    @PrimaryKey val query: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_progress")
data class QuizProgressEntity(
    @PrimaryKey val levelId: Int, // 1 to 10
    val isCompleted: Boolean = false, // true if bestScore >= 7
    val bestScore: Int = 0, // 0 to 10
    val attemptsCount: Int = 0,
    val isPerfect: Boolean = false, // true if bestScore == 10
    val earnedPoints: Int = 0, // bestScore + (if (bestScore == 10) 3 else 0)
    val lastPlayedTimestamp: Long = System.currentTimeMillis()
)
