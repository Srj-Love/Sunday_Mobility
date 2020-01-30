package com.example.sundaymobility.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserModel {
    @SerializedName("login")
    @Expose
    var login: String? = null

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

}