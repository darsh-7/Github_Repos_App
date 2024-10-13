package com.example.odcgithubrepoapp.data.repository

import RepoIssuesDomainModel
import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
import com.example.odcgithubrepoapp.data.mapper.toGithubReposDomainModel
import com.example.odcgithubrepoapp.data.mapper.toRepoDetailsDomainModel
import com.example.odcgithubrepoapp.data.mapper.toRepoIssuesDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.example.odcgithubrepoapp.domain.model.RepoDetailsDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import javax.inject.Inject

class GithubReposRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource,
): GithubReposRepository {
    override suspend fun fetchReposList(): List<GithubReposDomainModel> {
        return githubRemoteDataSource.fetchRepositoriesList().items.map {
            it.toGithubReposDomainModel()
        }
    }

    override suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDomainModel {
        return githubRemoteDataSource.fetchRepoDetails(ownerName, name).toRepoDetailsDomainModel()
    }

    override suspend fun fetchRepoIssues(ownerName: String, name: String) : List<RepoIssuesDomainModel> {
        return githubRemoteDataSource.fetchRepoIssues(ownerName, name).map {
            it.toRepoIssuesDomainModel()
        }
    }
}