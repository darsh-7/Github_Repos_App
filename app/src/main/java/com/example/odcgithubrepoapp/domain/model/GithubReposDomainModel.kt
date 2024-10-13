package com.example.odcgithubrepoapp.domain.model

data class GithubReposDomainModel(
    val id: Long,
    val avatar: String,
    val name: String,
    val ownerName: String,
    val description:String,
    val stars:Int
)
