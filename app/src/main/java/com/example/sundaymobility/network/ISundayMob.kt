package com.example.sundaymobility.network

import com.example.sundaymobility.model.UserModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ISundayMob {

    @GET("users")
    fun getUsers() : Observable<List<UserModel>>

}