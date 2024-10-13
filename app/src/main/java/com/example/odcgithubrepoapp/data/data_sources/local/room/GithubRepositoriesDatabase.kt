package com.example.odcgithubrepoapp.data.data_sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity

@Database(
    entities = [ReposListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubRepositoriesDatabase: RoomDatabase() {
    abstract fun repoListDao(): RepoListDao
}