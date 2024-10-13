package com.example.odcgithubrepoapp.presentation.common_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.model.CustomRemoteExceptionUiModel
import com.example.odcgithubrepoapp.presentation.theme.LightGray
import com.example.odcgithubrepoapp.presentation.theme.LightGreen


@Composable
fun ErrorSection(
    innerPadding: PaddingValues,
    onRefreshButtonClicked: () -> Unit,
    customErrorExceptionUiModel: CustomRemoteExceptionUiModel
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error_animation))
    val errorMessage = when(customErrorExceptionUiModel){
        is CustomRemoteExceptionUiModel.Timeout -> stringResource(R.string.timeout_exception_message)
        is CustomRemoteExceptionUiModel.NoInternetConnection -> stringResource(R.string.no_internet_connection_exception_message)
      //  is CustomExceptionUiModel.Network -> stringResource(R.string.network_exception_meesage)
        is CustomRemoteExceptionUiModel.ServiceUnreachable -> stringResource(R.string.service_unreachable_exception_message)
        is CustomRemoteExceptionUiModel.Unknown -> stringResource(R.string.unknown_exception_message)

    }
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .padding( bottom = 25.dp)
                .fillMaxWidth().height(340.dp)
        )

        Text(
            text = stringResource(id = R.string.something_went_wrong),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyLarge,
            color = LightGray,
        )
        
        Spacer(modifier = Modifier.height(80.dp))

        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
            border = BorderStroke(2.dp, LightGreen),
            modifier = Modifier
                .fillMaxWidth(0.8f),
            onClick = {
                onRefreshButtonClicked()
            }
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                fontSize = 18.sp,
                color = LightGreen,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewNoInternetConnection() {
    ErrorSection(
        innerPadding = PaddingValues(12.dp),
        onRefreshButtonClicked = {},
        customErrorExceptionUiModel = CustomRemoteExceptionUiModel.NoInternetConnection
    )
}

