package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.GithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoUiModelList
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@Composable
fun RepoItem(
    githubRepoUiModel: GithubReposUiModel,
    onRepoItemClicked: (String, String) -> Unit
) {
    val imageCrossFadeAnimationDuration = 1000
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onRepoItemClicked(githubRepoUiModel.ownerName, githubRepoUiModel.name)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = githubRepoUiModel.avatarUrl).apply {
                        crossfade(imageCrossFadeAnimationDuration)
                        placeholder(R.drawable.ic_github_placeholser)
                        error(R.drawable.ic_github_placeholser)
                    }.build()
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp)
                .size(50.dp)
                .clip(CircleShape)
        )

        Column(
            Modifier.padding(12.dp)
        ) {
            Row {
                Text(text = githubRepoUiModel.name, modifier = Modifier.weight(1f))
                Text(text =  githubRepoUiModel.starsCount)
                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            Text(githubRepoUiModel.ownerName, color = MaterialTheme.colorScheme.onSurface)
            Text(
                githubRepoUiModel.description,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 12.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview
@Composable
private fun PreviewRepoItem() {
    ODCGithubRepoAppTheme {
        RepoItem(
            githubRepoUiModel = fakeRepoUiModelList.first(),
            onRepoItemClicked = { _, _ ->
            }
        )
    }
}