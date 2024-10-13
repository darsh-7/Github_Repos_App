package com.example.odcgithubrepoapp.data.mapper.to_domain_modle

import RepoIssuesDomainModel
import android.util.Log
import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_issues.RepoIssuesDataModel
import com.example.odcgithubrepoapp.data.mapper.toCustomRemoteExceptionDomainModel

fun RepoIssuesDataModel.toRepoIssuesDomainModel(): RepoIssuesDomainModel {

      try {
          return RepoIssuesDomainModel(

              id = this.id,
              title = this.title,
              state = this.state,
              createdAt = this.created_at,
              description =this.body
          )
      } catch (e: Exception) {
          Log.e("toRepoIssuesDomainModel", "Error mapping RepoIssuesDataModel to RepoIssuesDomainModel error: ${e.message}")
            throw e.toCustomRemoteExceptionDomainModel()
      }

    }
