package com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.odcgithubrepoapp.domain.model.CustomRemoteExceptionDomainModel
import com.example.odcgithubrepoapp.domain.usecase.FetchRepositoryDetailsUseCase
import com.example.odcgithubrepoapp.presentation.mapper.toCustomExceptionRemoteUiModel
import com.example.odcgithubrepoapp.presentation.mapper.toRepoDetailsUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.model.RepoDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val fetchRepositoryDetailsUseCase: FetchRepositoryDetailsUseCase
) : ViewModel() {
    private val _repoDetailsStateFlow =
        MutableStateFlow<RepoDetailsUiState>(RepoDetailsUiState.InitialState)
    val repoDetailsStateFlow: StateFlow<RepoDetailsUiState> = _repoDetailsStateFlow.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _repoDetailsStateFlow.value = RepoDetailsUiState.Loading(isLoading = false)
        _repoDetailsStateFlow.value = RepoDetailsUiState.Error(
            (throwable as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
        )
    }

    fun requestRepoDetails(
        ownerName: String, name: String
    ) {
        _repoDetailsStateFlow.value = RepoDetailsUiState.Loading(isLoading = true)
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val repoDetails = fetchRepositoryDetailsUseCase(ownerName, name)
                _repoDetailsStateFlow.value = RepoDetailsUiState.Loading(isLoading = false)
                _repoDetailsStateFlow.value = RepoDetailsUiState.RepoDetailsUiModelData(repoDetails.toRepoDetailsUiModel())
            } catch (e: Exception) {
                _repoDetailsStateFlow.value = RepoDetailsUiState.Loading(isLoading = false)
                _repoDetailsStateFlow.value = RepoDetailsUiState.Error(
                    (e as CustomRemoteExceptionDomainModel).toCustomExceptionRemoteUiModel()
                )

            }
        }
    }
}