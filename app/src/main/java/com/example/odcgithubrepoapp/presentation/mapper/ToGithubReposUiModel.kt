package com.example.odcgithubrepoapp.presentation.mapper

import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.GithubReposUiModel as GithubReposUiModel


fun GithubReposDomainModel.toGithubReposUiModel(): GithubReposUiModel {
    return GithubReposUiModel(
        id = this.id,
        name = this.name,
        avatarUrl = this.avatar,
        description = this.description,
        starsCount = this.stars.toString(),
        ownerName = this.ownerName
    )
}