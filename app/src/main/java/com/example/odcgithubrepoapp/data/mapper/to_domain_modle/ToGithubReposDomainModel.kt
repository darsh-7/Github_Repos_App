package com.example.odcgithubrepoapp.data.mapper.to_domain_modle

import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel

//fun Item.toGithubReposDomainModel(): GithubReposDomainModel {
//    return GithubReposDomainModel(
//        id = this.id,
//        name = this.name,
//        ownerName = this.owner.login,
//        avatar = this.owner.avatar_url,
//        stars = this.stargazers_count.toString(),
//        description = this.description
//    )
//}
fun ReposListEntity.toGithubReposDomainModel(): GithubReposDomainModel {
    return GithubReposDomainModel(
        id = this.id,
        name = this.name,
        ownerName = this.ownerName,
        avatar = this.avatar,
        stars = this.starsCount,
        description = this.description
    )
}