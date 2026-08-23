package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY addedTimestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE articleId = :articleId LIMIT 1")
    fun getFavoriteByArticleId(articleId: Int): Flow<FavoriteEntity?>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE articleId = :articleId)")
    fun isFavorite(articleId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE articleId = :articleId")
    suspend fun deleteFavorite(articleId: Int)

    @Query("SELECT DISTINCT folderTag FROM favorites")
    fun getAllTags(): Flow<List<String>>
}

@Dao
interface NoteDao {
    @Query("SELECT * FROM article_notes ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM article_notes WHERE articleId = :articleId ORDER BY updatedAt DESC")
    fun getNotesForArticle(articleId: Int): Flow<List<NoteEntity>>

    @Query("SELECT * FROM article_notes WHERE id = :id LIMIT 1")
    suspend fun getNoteById(id: Long): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("DELETE FROM article_notes WHERE id = :id")
    suspend fun deleteNoteById(id: Long)
}

@Dao
interface RecentSearchDao {
    @Query("SELECT query FROM recent_searches ORDER BY timestamp DESC LIMIT 10")
    fun getRecentSearches(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: RecentSearchEntity)

    @Query("DELETE FROM recent_searches WHERE query = :query")
    suspend fun deleteSearch(query: String)

    @Query("DELETE FROM recent_searches")
    suspend fun clearAllSearches()
}
