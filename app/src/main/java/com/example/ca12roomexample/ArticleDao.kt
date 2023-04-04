package com.example.ca12roomexample

import androidx.room.*
import com.example.ca12roomexample.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)

    @Update
    fun updateArticle(article: Article)

    @Delete
    fun deleteArticle(article: Article)

    @Query("SELECT * FROM article")
    fun loadAllArticles(): List<Article>

}