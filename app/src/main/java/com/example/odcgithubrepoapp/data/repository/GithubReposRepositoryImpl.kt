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
        }
    }

    override suspend fun fetchReposListCash(): List<GithubReposDomainModel> {
            val respond: List<ReposListEntity> = githubLocalDataSource.getTrendingList()
            if (respond.isNotEmpty()) {
                return respond.map {
                    it.toGithubReposDomainModel()
                }
            }
       throw CustomRemoteExceptionDomainModel.ServiceNotFoundLocalException
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