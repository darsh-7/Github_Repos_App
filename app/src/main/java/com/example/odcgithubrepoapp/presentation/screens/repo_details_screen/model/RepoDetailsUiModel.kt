package com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.model

data class RepoDetailsUiModel(
    val id:Long,
    val name: String,
    val avatar: String,
    val description: String,
    val forks: String,
    val language: String,
    val fullName: String,
    val stars: String,
    val url: String,
    val owner: String,
    val createdAt: String
)