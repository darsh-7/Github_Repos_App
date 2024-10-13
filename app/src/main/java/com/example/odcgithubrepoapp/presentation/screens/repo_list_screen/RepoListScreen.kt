package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component.AnimateShimmerRepoList
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component.RepoItem
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoListUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoListErrorUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoListLoadingUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoListUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.viewmodel.RepoListViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import kotlinx.coroutines.launch

@Composable
fun RepoListScreen(
    onRepoItemClicked: (String, String) -> Unit
) {
    val repoListViewModel: RepoListViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        repoListViewModel.requestGithubRepoList()
    }
    val repoListUiSate by repoListViewModel.repoListStateFlow.collectAsStateWithLifecycle()

    RepoListContent(
        repoListUiSate = repoListUiSate,
        onRefreshButtonClicked = {
            repoListViewModel.requestGithubRepoList()
        },
        onRepoItemClicked = onRepoItemClicked
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListContent(
    modifier: Modifier = Modifier,
    repoListUiSate: RepoListUiState,
    onRefreshButtonClicked: () -> Unit,
    onRepoItemClicked: (String, String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AppBar(
                title = R.string.app_name,
                showBackButton = false
            )
        }
    ) { innerPadding ->
        when {
            repoListUiSate.isLoading -> {
                AnimateShimmerRepoList(
                    innerPadding = innerPadding
                )
            }

            repoListUiSate.isError -> {
                ErrorSection(
                    innerPadding = innerPadding,
                    customErrorExceptionUiModel = repoListUiSate.customRemoteExceptionUiModel,
                    onRefreshButtonClicked = {
                        onRefreshButtonClicked()
                    }
                )
            }



            repoListUiSate.repoList.isNotEmpty() -> {
                if (repoListUiSate.snackBarError) SnakeBarError(
                    snackbarHostState = snackbarHostState,
                    repoListUiSate = repoListUiSate,
                    onRefreshButtonClicked = onRefreshButtonClicked
                )


                LazyColumn(
                    Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                ) {
                    items(repoListUiSate.repoList) { githubRepoUiModel ->
                        RepoItem(
                            githubRepoUiModel = githubRepoUiModel,
                            onRepoItemClicked = onRepoItemClicked
                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun SnakeBarError(
    snackbarHostState: SnackbarHostState,
    repoListUiSate: RepoListUiState,
    onRefreshButtonClicked: () -> Unit
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            val result = snackbarHostState
                .showSnackbar(
                    message =  repoListUiSate.customRemoteExceptionUiModel.toString(),
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite
                )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    onRefreshButtonClicked()
                }
                SnackbarResult.Dismissed -> {
                    // do nothing
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewRepoListScreen() {
    ODCGithubRepoAppTheme {
        RepoListContent(
            repoListUiSate = fakeRepoListUiState,
            onRefreshButtonClicked = {},
            onRepoItemClicked = { _, _ -> }
        )
    }
}

@Preview
@Composable
private fun PreviewRepoListScreenLoading() {
    ODCGithubRepoAppTheme {
        RepoListContent(
            repoListUiSate = fakeRepoListLoadingUiState,
            onRefreshButtonClicked = {},
            onRepoItemClicked = { _, _ -> }
        )
    }
}

@Preview
@Composable
private fun PreviewRepoListScreenError() {
    ODCGithubRepoAppTheme {
        RepoListContent(
            repoListUiSate = fakeRepoListErrorUiState,
            onRefreshButtonClicked = {},
            onRepoItemClicked = { _, _ -> }
        )
    }
}




