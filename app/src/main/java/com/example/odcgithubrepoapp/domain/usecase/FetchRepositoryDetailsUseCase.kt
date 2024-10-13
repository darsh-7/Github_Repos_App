package com.example.odcgithubrepoapp.domain.usecase

import com.example.odcgithubrepoapp.domain.model.RepoDetailsDomainModel
import com.example.odcgithubrepoapp.domain.repository.GithubReposRepository
import javax.inject.Inject

class FetchRepositoryDetailsUseCase @Inject constructor(
    private val githubReposRepository: GithubReposRepository
) {
    suspend operator fun invoke(
        ownerName:String, name:String
    ): RepoDetailsDomainModel  {
        return githubReposRepository.fetchRepoDetails(ownerName, name)
    }
}
