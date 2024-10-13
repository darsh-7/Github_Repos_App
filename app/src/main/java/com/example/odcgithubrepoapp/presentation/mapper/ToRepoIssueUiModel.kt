package com.example.odcgithubrepoapp.presentation.mapper

import RepoIssuesDomainModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoIssuesUiModel


fun RepoIssuesDomainModel.toRepoIssuesUiModel(): RepoIssuesUiModel {
    return RepoIssuesUiModel(
        id = this.id,
        title = this.title,
        state = this.state,
        description = this.description,
        createdAt = this.createdAt
    )
}