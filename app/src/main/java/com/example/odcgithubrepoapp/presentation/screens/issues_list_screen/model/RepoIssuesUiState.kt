package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model

import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel


data class RepoIssuesUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val customRemoteExceptionUiModel: CustomRemoteExceptionUiModel = CustomRemoteExceptionUiModel.Unknown,
    val repoIssues: List<RepoIssuesUiModel> = emptyList()
)
