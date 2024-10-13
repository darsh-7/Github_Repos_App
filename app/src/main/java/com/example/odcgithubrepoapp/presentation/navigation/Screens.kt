package com.example.odcgithubrepoapp.presentation.navigation

import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.ISSUES_LIST_SCREEN
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.NAME_ARGUMENT_KEY
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.OWNER_ARGUMENT_KEY
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.REPO_DETAILS_SCREEN
import com.example.odcgithubrepoapp.presentation.utils.Constants.Companion.REPO_LIST_SCREEN






sealed class Screens(val route: String) {
    data object RepoListScreen: Screens(REPO_LIST_SCREEN)

    data object RepoDetailsScreen: Screens("$REPO_DETAILS_SCREEN/{$OWNER_ARGUMENT_KEY}/{$NAME_ARGUMENT_KEY}"){
        fun passOwnerAndName(owner:String, name:String):String{
            return "$REPO_DETAILS_SCREEN/$owner/$name"
        }
    }

    data object IssuesListScreen: Screens("$ISSUES_LIST_SCREEN/{$OWNER_ARGUMENT_KEY}/{$NAME_ARGUMENT_KEY}"){
        fun passOwnerAndNameIssue(owner:String, name:String):String{
            return "$ISSUES_LIST_SCREEN/$owner/$name"
        }
    }
}