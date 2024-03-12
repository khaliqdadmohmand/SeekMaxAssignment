package com.loc.seekmax.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface JobsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url=:url")
    suspend fun getArticle(url: String): Article?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE id=:id")
    fun getUser(id: Int): User?

    @Query("SELECT * FROM User WHERE firstname=:firstname & password=:password")
    suspend fun loginUser(firstname: String, password: String): User?

}