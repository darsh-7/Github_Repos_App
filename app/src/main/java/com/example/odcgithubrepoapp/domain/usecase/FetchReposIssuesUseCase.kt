package com.example.odcgithubrepoapp.domain.usecase

import RepoIssuesDomainModel
import android.util.Log
import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import javax.inject.Inject

class FetchReposIssuesUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(
        ownerName:String, name:String
        ): List<RepoIssuesDomainModel> {
        Log.i("FetchReposIssuesUseCase", "invoke with value: $ownerName ,$name.")

        return githubReposRepository.fetchRepoIssues(ownerName = ownerName, name = name)
    }
}

// val repositoryListApi: RepositoriesListApi = Retrofit
// val githubRemoteDataSource =  GithubRemoteDataSource(repositoryListApi)
// val githubReposRepository: GithubReposRepository = GithubReposRepositoryImpl(githubRemoteDataSource)
// val fetchGithubReposListUseCase = FetchGithubReposListUseCase(githubReposRepository)
// RepoListViewModel( fetchGithubReposListUseCase)