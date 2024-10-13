package com.example.odcgithubrepoapp.data.data_sources.local

import com.example.odcgithubrepoapp.data.data_sources.local.data_store.DataStorePreference
import com.example.odcgithubrepoapp.data.data_sources.local.room.RepoListDao
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import javax.inject.Inject

class GithubLocalDataSource @Inject constructor(
    private val repoListDao: RepoListDao,
   // private val dataStorePreference: DataStorePreference,
) {
    suspend fun getTrendingList(): List<ReposListEntity> {
        return repoListDao.getReposList()
    }

    suspend fun insertRepos(repoList: List<ReposListEntity>) {
        repoListDao.insertReposList(repoList)
    }

}