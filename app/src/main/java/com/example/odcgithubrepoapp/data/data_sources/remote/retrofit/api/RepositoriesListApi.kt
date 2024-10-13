package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.api

import com.example.odcgithubrepoapp.data.Constants.Companion.GITHUB_REPOS_ENDPOINT
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.GithubReposDataModel
import retrofit2.Response
import retrofit2.http.GET

interface RepositoriesListApi {
    @GET(GITHUB_REPOS_ENDPOINT)
    suspend fun fetchRepositoriesList(): Response<GithubReposDataModel>
}