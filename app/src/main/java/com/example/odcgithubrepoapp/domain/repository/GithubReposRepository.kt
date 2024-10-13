package com.example.odcgithubrepoapp.domain.repository

import RepoIssuesDomainModel
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.example.odcgithubrepoapp.domain.model.RepoDetailsDomainModel

interface GithubReposRepository {
    suspend fun fetchReposList(): List<GithubReposDomainModel>
    suspend fun fetchRepoDetails(ownerName: String, name: String): RepoDetailsDomainModel
    suspend fun fetchRepoIssues(ownerName: String, name: String) : List<RepoIssuesDomainModel>
}