package com.example.sundaymobility.network

import retrofit2.Response
import java.io.IOException

abstract class SafeApiCall {

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful){
            return response.body()!!
        }else{
            //TODO: handle Api Exceptions
            throw ApiException(response.code().toString())
        }
    }

}

class ApiException(message: String): IOException(message)
