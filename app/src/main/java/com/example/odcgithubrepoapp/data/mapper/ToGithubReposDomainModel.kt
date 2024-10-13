package com.example.odcgithubrepoapp.data.mapper

import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.Item
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel

fun Item.toGithubReposDomainModel(): GithubReposDomainModel {
    return GithubReposDomainModel(
        id = this.id,
        name = this.name,
        ownerName = this.owner.login,
        avatar = this.owner.avatar_url,
        stars = this.stargazers_count,
        description = this.description
    )
}