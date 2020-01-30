package com.example.sundaymobility.utils

import com.example.sundaymobility.network.ISundayMob
import com.example.sundaymobility.network.RetrofitClient

class Constants {

    companion object {
        val REQUEST_SUCCESS = 521
        val REQUEST_FAILED = 212
        val PHOTO_REQUEST_CODE = 200

        val BASE_URL = "https://api.github.com/"
        fun getAPI(): ISundayMob =
            RetrofitClient.getClient(BASE_URL)!!.create(ISundayMob::class.java)

    }
}