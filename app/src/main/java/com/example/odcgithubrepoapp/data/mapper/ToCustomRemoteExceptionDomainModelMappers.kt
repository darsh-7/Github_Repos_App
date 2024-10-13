package com.example.odcgithubrepoapp.data.mapper

import android.util.Log
import com.example.odcgithubrepoapp.domain.model.CustomRemoteExceptionDomainModel
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.HttpURLConnection

fun Exception.toCustomRemoteExceptionDomainModel(): CustomRemoteExceptionDomainModel {
    Log.d("Exception", this.toString())
    return when(this){
        is InterruptedIOException -> CustomRemoteExceptionDomainModel.TimeOutExceptionRemoteException
        is IOException -> CustomRemoteExceptionDomainModel.NoInternetConnectionRemoteException
        is HttpException -> {
            when(this.code()){
                HttpURLConnection.HTTP_NOT_FOUND -> CustomRemoteExceptionDomainModel.ServiceNotFoundRemoteException
                HttpURLConnection.HTTP_FORBIDDEN -> CustomRemoteExceptionDomainModel.AccessDeniedRemoteException
                HttpURLConnection.HTTP_UNAVAILABLE -> CustomRemoteExceptionDomainModel.ServiceUnavailableRemoteException
                else -> CustomRemoteExceptionDomainModel.UnknownRemoteException
            }
        }
        else -> CustomRemoteExceptionDomainModel.UnknownRemoteException
    }
}