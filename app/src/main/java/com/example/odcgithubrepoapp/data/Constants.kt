package com.example.odcgithubrepoapp.data

class Constants {
    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val GITHUB_REPOS_ENDPOINT = "search/repositories?q=language"

        // room
        const val GITHUB_REPOSITORIES_TABLE = "github_repositories_table"

        // data store
        const val PREFERENCES_NAME = "trending_preference"
        const val PREFERENCES_IS_FIRST_TIME = "is_first_time"

        // keys
        const val OWNER_KEY = "owner"
        const val REPO_NAME_KEY = "repo"

    }
}