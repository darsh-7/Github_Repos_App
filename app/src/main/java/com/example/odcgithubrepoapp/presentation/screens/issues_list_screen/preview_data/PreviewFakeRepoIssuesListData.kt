
import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoIssuesUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoIssuesUiState

val fakeRepoIssuesUiModelList = listOf(
    RepoIssuesUiModel(
        id = 1,
        title = "Issue 1",
        state = "open",
        description = "Multiplatform coroutines for Kotlin",
        createdAt = "2021-09-01T00:00:00Z",
    ),
    RepoIssuesUiModel(
        id = 2,
        title = "Issue 2",
        state = "open",
        description = "Declarative UI toolkit for Android",
        createdAt = "2021-09-02T00:00:00Z",
    ),
    RepoIssuesUiModel(
        id = 3,
        title = "Issue 3",
        state = "open",
        description = "Type-safe HTTP client for Android and Java",
        createdAt = "2021-09-03T00:00:00Z",
    ),
    RepoIssuesUiModel(
        id = 4,
        title = "Issue 4",
        state = "open",
        description = "Efficient HTTP client for Android and Java",
        createdAt = "2021-09-04T00:00:00Z",
    ),
    RepoIssuesUiModel(
        id = 5,
        title = "Issue 5",
        state = "open",
        description = "SQLite database access object",
        createdAt = "2021-09-05T00:00:00Z",
    )
)

val fakeRepoIssuesUiState = RepoIssuesUiState(
    isLoading = false,
    isError = false,
    repoIssues = fakeRepoIssuesUiModelList
)
val fakeRepoIssuesLoadingUiState = RepoIssuesUiState(
    isLoading = true,
)
val fakeRepoIssuesErrorUiState = RepoIssuesUiState(
    isLoading = false,
    isError = true,
    customRemoteExceptionUiModel = CustomRemoteExceptionUiModel.NoInternetConnection
)
