package com.example.ca12roomexample

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Article::class],
    version = 1
)

abstract class NewsDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}