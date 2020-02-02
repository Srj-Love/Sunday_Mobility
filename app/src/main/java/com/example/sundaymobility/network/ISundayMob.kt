package com.example.sundaymobility.network

import com.example.sundaymobility.model.UserModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ISundayMob {

    @GET("users")
    suspend fun getUsers() : Response<List<UserModel>>

}