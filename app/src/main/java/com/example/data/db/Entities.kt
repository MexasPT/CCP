package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

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
