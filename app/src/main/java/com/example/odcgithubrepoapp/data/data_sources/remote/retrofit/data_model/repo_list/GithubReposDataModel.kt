package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list

import com.google.gson.annotations.SerializedName

data class GithubReposDataModel(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("total_count")
    val totalCount: Long,
    val items: List<Item>
)