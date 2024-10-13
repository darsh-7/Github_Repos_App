package com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_issues

data class PullRequest(
    val diff_url: String,
    val html_url: String,
    val merged_at: Any,
    val patch_url: String,
    val url: String
)