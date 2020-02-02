package com.example.sundaymobility.repository

import com.example.sundaymobility.network.ISundayMob
import com.example.sundaymobility.network.SafeApiCall

class UserRepository (private val api: ISundayMob) : SafeApiCall() {

    suspend fun getUsers() = apiCall { api.getUsers() }
}
