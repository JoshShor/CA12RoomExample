package com.example.ca12roomexample

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NewsDataBaseTest {
    private lateinit var db: NewsDataBase
    private lateinit var articleDao: ArticleDao
    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NewsDataBase::class.java).build()
        articleDao = db.articleDao()
        initData()
    }
    private fun initData(){
        val articleNumber = 5
        for(i in 0 until articleNumber){
            val article = Article(title = "title$i",
                content = "content$i",
                time = System.currentTimeMillis()
            )
            articleDao.insertArticle(article)
        }
        val articles = articleDao.loadAllArticles()
        assertEquals(5, articles.size)
    }
    @After
    @Throws(IOException::class)
    fun tearDown(){
        db.close()
    }
    @Test
    fun updateArticle(){
        val article = articleDao.loadAllArticles()[0]
        articleDao.updateArticle(article.copy(title = "new title"))
        assertEquals("new title", articleDao.loadAllArticles()[0].title)
    }
}