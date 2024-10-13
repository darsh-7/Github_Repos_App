
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.common_component.shimmer.repo_list.AnimateShimmerRepoList
import com.example.odcgithubrepoapp.presentation.screens.issues_list_screen.component.IssuesItem
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.RepoListContent
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoIssuesUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.viewmodel.RepoIssuesViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme


@Composable
fun IssuesListScreen(
    owner: String,
    name: String,
    onClickBack: () -> Unit,
) {

    val repoIssuesViewModel: RepoIssuesViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        repoIssuesViewModel.requestRepoIssues(ownerName = owner, name = name)
        Log.i("IssuesListScreen", "requestRepoIssues from the model ownerName: $owner, name: $name")
    }

    val repoIssuesUiState by repoIssuesViewModel.repoIssuesStateFlow.collectAsStateWithLifecycle()



    IssuesListContent(
        repoIssuesUiState = repoIssuesUiState,
        onClickBack = onClickBack
    )
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun IssuesListContent(
    repoIssuesUiState: RepoIssuesUiState,
        modifier: Modifier = Modifier,
        onClickBack: () -> Unit,
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            topBar = {
                AppBar(
                    onBackButtonClicked = onClickBack,
                    title = R.string.details_app_bar_title
                )
            }
        ) { innerPadding ->

            when {
                repoIssuesUiState.isLoading -> {
                    AnimateShimmerRepoList(
                        innerPadding = innerPadding
                    )
                }
                repoIssuesUiState.isError -> {
                    ErrorSection(
                        innerPadding = innerPadding,
                        customErrorExceptionUiModel = repoIssuesUiState.customRemoteExceptionUiModel,
                        onRefreshButtonClicked = {
                           // onRefreshButtonClicked()
                        }
                    )
                }
                repoIssuesUiState.repoIssues.isNotEmpty() -> {
                    LazyColumn(
                        Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                    ) {
                        items(repoIssuesUiState.repoIssues) { repoItem ->
                            IssuesItem(repoIssuesUiModel = repoItem)
                        }
                    }
                }
    }

}    }

@Preview
@Composable
private fun PreviewRepoIssuesScreen() {
    ODCGithubRepoAppTheme {
        IssuesListContent(
            repoIssuesUiState = fakeRepoIssuesUiState,
            onClickBack = {}
        )
    }
}

@Preview
@Composable
private fun PreviewRepoIssuesScreenLoading() {
    ODCGithubRepoAppTheme {
        RepoListContent(
            repoListUiSate = fakeRepoIssuesLoadingUiState ,
            onRefreshButtonClicked = {},
            onRepoItemClicked = {_,_ -> }
        )
    }
}

@Preview
@Composable
private fun PreviewRepoIssuesScreenError() {
    ODCGithubRepoAppTheme {
        RepoListContent(
            repoListUiSate = fakeRepoIssuesErrorUiState,
            onRefreshButtonClicked = {},
            onRepoItemClicked = {_,_ -> }
        )
    }
}


