package com.loc.seekmax.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.loc.seekmax.domain.model.Article
import com.loc.seekmax.domain.model.User

@Database(entities = [Article::class, User::class],version = 2,)
@TypeConverters(JobsTypeConvertor::class)
abstract class JobsDatabase : RoomDatabase() {

    abstract val jobsDao: JobsDao

}