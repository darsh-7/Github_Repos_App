package com.example.odcgithubrepoapp.domain.model

sealed class CustomRemoteExceptionDomainModel: Exception() {
    data object NoInternetConnectionRemoteException: CustomRemoteExceptionDomainModel() {
        private fun readResolve(): Any = NoInternetConnectionRemoteException
    }

    data object TimeOutExceptionRemoteException: CustomRemoteExceptionDomainModel() {
        private fun readResolve(): Any = TimeOutExceptionRemoteException
    }

    data object AccessDeniedRemoteException: CustomRemoteExceptionDomainModel() {
        private fun readResolve(): Any = AccessDeniedRemoteException
    }

    data object ServiceUnavailableRemoteException: CustomRemoteExceptionDomainModel() {
        private fun readResolve(): Any = ServiceUnavailableRemoteException
    }

    data object ServiceNotFoundRemoteException: CustomRemoteExceptionDomainModel() {
        private fun readResolve(): Any = ServiceNotFoundRemoteException
    }

    data object UnknownRemoteException: CustomRemoteExceptionDomainModel() {
        private fun readResolve(): Any = UnknownRemoteException
    }
}