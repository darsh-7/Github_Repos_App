package com.example.odcgithubrepoapp.presentation.common_component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@ExperimentalMaterial3Api
@Composable
fun AppBar(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    onBackButtonClicked: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(title),
                style = titleStyle
            )
        },
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            if (showBackButton) {
                IconButton(
                    onClick = { onBackButtonClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun PreviewAppBar() {
      ODCGithubRepoAppTheme {
          AppBar(
              title = R.string.app_name,
              showBackButton = false
          )
      }
}