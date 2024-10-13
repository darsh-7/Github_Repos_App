package com.example.odcgithubrepoapp.presentation.mapper

import com.example.odcgithubrepoapp.domain.model.RepoDetailsDomainModel
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.model.RepoDetailsUiModel

fun RepoDetailsDomainModel.toRepoDetailsUiModel(): RepoDetailsUiModel {
    return RepoDetailsUiModel(
        id = id,
        name = name,
        avatar = avatar,
        description = description,
        stars = stars.toString(),
        owner = owner,
        forks = forks.toString(),
        language = language,
        fullName = fullName,
        url = url,
        createdAt = createdAt
    )
}