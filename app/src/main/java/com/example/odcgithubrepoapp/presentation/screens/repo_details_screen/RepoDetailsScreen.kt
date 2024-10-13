import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.components.AnimateShimmerDetails
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.preview_data.fakeRepoDetailsUiModel
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.common_component.AppBar
import com.example.odcgithubrepoapp.presentation.common_component.ErrorSection
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.components.DetailsItem
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.model.RepoDetailsUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.model.RepoDetailsUiState
import com.example.odcgithubrepoapp.presentation.screens.repo_details_screen.viewmodel.RepoDetailsViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoDetailsScreen(
    modifier: Modifier = Modifier,
    owner: String,
    name: String,
    onClickBack: () -> Unit,
    onShowIssuesClicked: () -> Unit
) {
    val repoDetailsViewModel: RepoDetailsViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        repoDetailsViewModel.requestRepoDetails(ownerName = owner, name = name)
    }

    val repoDetailsUiState by repoDetailsViewModel.repoDetailsStateFlow.collectAsStateWithLifecycle()

    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = {
            AppBar(
                onBackButtonClicked = onClickBack,
                title = R.string.details_app_bar_title
            )
        }
    ) { innerPadding ->

        when (val result = repoDetailsUiState) {
            is RepoDetailsUiState.InitialState -> {}

            is RepoDetailsUiState.Loading -> {
                if (result.isLoading)
                    AnimateShimmerDetails(
                        innerPadding = innerPadding
                    )
            }

            is RepoDetailsUiState.RepoDetailsUiModelData -> {
                DetailsContent(
                    innerPadding = innerPadding,
                    repoDetailsUiModel = result.repositoryDetails,
                    onShowIssuesClicked = onShowIssuesClicked
                )
            }

            is RepoDetailsUiState.Error -> {
                ErrorSection(
                    innerPadding = innerPadding,
                    customErrorExceptionUiModel = result.customErrorExceptionUiModel,
                    onRefreshButtonClicked = {
                        repoDetailsViewModel.requestRepoDetails(ownerName = owner, name = name)
                    }
                )
            }
        }
    }
}

@Composable
fun DetailsContent(
    innerPadding: PaddingValues,
    repoDetailsUiModel: RepoDetailsUiModel,
    onShowIssuesClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = repoDetailsUiModel.avatar)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(1000)
                        placeholder(R.drawable.ic_github_placeholser)
                    })
                    .build()
            ),
            contentDescription = stringResource(R.string.accessbility_details_your_avatar_image),
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(150.dp))
                .padding(top = 16.dp),
        )

        Text(
            text = repoDetailsUiModel.name,
            style = MaterialTheme.typography.headlineSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DetailsItem(
                value = repoDetailsUiModel.stars,
                image = R.drawable.ic_star,
                modifier = Modifier.weight(1f),
                colorFilter = ColorFilter.tint(Color.Yellow),
            )
            Row {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Blue)
                )
                Text(
                    text = repoDetailsUiModel.language,
                    modifier = Modifier
                        .padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            DetailsItem(
                value = repoDetailsUiModel.forks,
                image = R.drawable.ic_fork,
                modifier = Modifier.weight(1f),
            )
        }
        Text(
            text = repoDetailsUiModel.description,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onShowIssuesClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.show_issues),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    ODCGithubRepoAppTheme {
        DetailsContent(
            innerPadding = PaddingValues(12.dp),
            repoDetailsUiModel = fakeRepoDetailsUiModel,
            onShowIssuesClicked = {},
        )
    }
}