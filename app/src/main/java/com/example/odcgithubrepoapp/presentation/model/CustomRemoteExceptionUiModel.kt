package com.example.odcgithubrepoapp.presentation.model

sealed class CustomRemoteExceptionUiModel {

    data object NoInternetConnection : CustomRemoteExceptionUiModel()

    data object Timeout : CustomRemoteExceptionUiModel()

    data object ServiceUnreachable : CustomRemoteExceptionUiModel()

    data object Unknown : CustomRemoteExceptionUiModel()

}