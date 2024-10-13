package com.example.odcgithubrepoapp.presentation.screens.issues_list_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.RepoIssuesUiModel
import fakeRepoIssuesUiModelList

@Composable
fun IssuesItem(
    repoIssuesUiModel: RepoIssuesUiModel

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

            }
    ) {
        Image(
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp)
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_issues),
            contentDescription = null,
        )

        Column(
            Modifier.padding(12.dp)
        ) {
            Row {
                Text(text = repoIssuesUiModel.title, modifier = Modifier.weight(1f), maxLines = 1)
                Text(text = repoIssuesUiModel.state, maxLines = 1)

            }

            Text(repoIssuesUiModel.description, color = MaterialTheme.colorScheme.onSurface, maxLines = 2, minLines = 2)

            Text(
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 12.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Created At: ")
                    }
                    append(repoIssuesUiModel.createdAt)
                })
        }

    }
}

@Preview
@Composable
private fun PreviewRepoItem() {
    //  ODCGithubRepoAppTheme {
    IssuesItem(
        fakeRepoIssuesUiModelList.first()
    )
    // }
}