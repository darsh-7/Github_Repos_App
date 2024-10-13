package com.example.odcgithubrepoapp.domain.usecase

import com.example.odcgithubrepoapp.domain.model.GithubReposDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import javax.inject.Inject

class FetchGithubReposListCacheUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(): List<GithubReposDomainModel> {
        return githubReposRepository.fetchReposListCash()
    }
}

// val repositoryListApi: RepositoriesListApi = Retrofit
// val githubRemoteDataSource =  GithubRemoteDataSource(repositoryListApi)
// val githubReposRepository: GithubReposRepository = GithubReposRepositoryImpl(githubRemoteDataSource)
// val fetchGithubReposListUseCase = FetchGithubReposListUseCase(githubReposRepository)
// RepoListViewModel( fetchGithubReposListUseCase)