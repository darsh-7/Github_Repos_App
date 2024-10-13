package com.example.odcgithubrepoapp.di

import android.content.Context
import androidx.room.Room
import com.example.odcgithubrepoapp.data.data_sources.local.room.GithubRepositoriesDatabase
import com.example.odcgithubrepoapp.data.data_sources.local.room.RepoListDao
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGithubRepositoriesDatabase(
        @ApplicationContext context: Context
    ):GithubRepositoriesDatabase {
        return Room.databaseBuilder(
            context,
            GithubRepositoriesDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepoListDao(
        githubRepositoriesDatabase: GithubRepositoriesDatabase
    ):RepoListDao {
        return githubRepositoriesDatabase.repoListDao()
    }
}