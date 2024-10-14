package com.example.odcgithubrepoapp.data.repository

import RepoIssuesDomainModel
import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.to_domain_modle.toGithubReposDomainModel
import com.example.odcgithubrepoapp.data.mapper.to_domain_modle.toRepoDetailsDomainModel
import com.example.odcgithubrepoapp.data.mapper.to_domain_modle.toRepoIssuesDomainModel
import com.example.odcgithubrepoapp.data.mapper.to_entity.toGithubReposEntity
import com.example.odcgithubrepoapp.domain.model.CustomRemoteExceptionDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.example.odcgithubrepoapp.domain.model.RepoDetailsDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubReposRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource,
) : GithubReposRepository {

    override suspend fun fetchReposList(): List<GithubReposDomainModel> {
        val respond: List<ReposListEntity> =
            githubRemoteDataSource.fetchRepositoriesList().items.map {
                it.toGithubReposEntity()
            }
        if (respond.isNotEmpty()) {
            githubLocalDataSource.insertRepos(respond)
        }
        return respond.map {
            it.toGithubReposDomainModel()
        }.also {
            if (it.isNotEmpty()) {
              githubLocalDataSource.saveIsFirstTimeEnterApp(false)
            } else {
                throw CustomRemoteExceptionDomainModel.ServiceNotFoundRemoteException
            }

        }
    }

    override suspend fun fetchReposListCash(): List<GithubReposDomainModel> {
        if (!githubLocalDataSource.readIsFirstTimeEnterApp()) {
            val respond: List<ReposListEntity> = githubLocalDataSource.getTrendingList()
            return respond.map {
                it.toGithubReposDomainModel()
            }.also {
                if (it.isEmpty()) {
                   githubLocalDataSource.saveIsFirstTimeEnterApp(true)
                    throw CustomRemoteExceptionDomainModel.ServiceNotFoundLocalException
                }
            }
        } else {
            throw CustomRemoteExceptionDomainModel.ServiceNotFoundLocalException
        }
    }

    override suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDomainModel {
        return githubRemoteDataSource.fetchRepoDetails(ownerName, name).toRepoDetailsDomainModel()
    }

    override suspend fun fetchRepoIssues(
        ownerName: String,
        name: String
    ): List<RepoIssuesDomainModel> {
        return githubRemoteDataSource.fetchRepoIssues(ownerName, name).map {
            it.toRepoIssuesDomainModel()
        }
    }
}