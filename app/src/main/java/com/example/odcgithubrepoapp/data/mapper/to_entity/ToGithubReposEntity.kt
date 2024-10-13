package com.example.odcgithubrepoapp.data.mapper.to_entity

import com.example.odcgithubrepoapp.data.data_sources.local.room.entities.ReposListEntity
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.Item

fun Item.toGithubReposEntity(): ReposListEntity {
    return ReposListEntity(
        id = this.id,
        name = this.name  ,
        ownerName = this.owner.login  ,
        avatar = this.owner.avatar_url  ,
        starsCount = this.stargazers_count.toString()  ,
        description = this.description ,
    )
}
