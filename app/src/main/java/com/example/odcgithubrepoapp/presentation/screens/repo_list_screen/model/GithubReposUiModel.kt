package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model

data class GithubReposUiModel(
    val id: Long,
    val avatarUrl:String,
    val name:String,
    val ownerName:String,
    val description:String,
    val starsCount:String,
)
