package com.example.sundaymobility.utils

import com.example.sundaymobility.model.UserModel

object data {


    val imageUrlList = arrayListOf(
        "https://avatars0.githubusercontent.com/u/1?v=4",
        "https://avatars0.githubusercontent.com/u/2?v=4",
        "https://avatars0.githubusercontent.com/u/3?v=4",
        "https://avatars0.githubusercontent.com/u/4?v=4",
        "https://avatars0.githubusercontent.com/u/5?v=4",
        "https://avatars0.githubusercontent.com/u/6?v=4",
        "https://avatars0.githubusercontent.com/u/7?v=4",
        "https://avatars1.githubusercontent.com/u/17?v=4",
        "https://avatars0.githubusercontent.com/u/18?v=4",
        "https://avatars0.githubusercontent.com/u/19?v=4",
        "https://avatars3.githubusercontent.com/u/20?v=4",
        "https://avatars3.githubusercontent.com/u/21?v=4",
        "https://avatars3.githubusercontent.com/u/22?v=4",
        "https://avatars3.githubusercontent.com/u/23?v=4",
        "https://avatars3.githubusercontent.com/u/25?v=4",
        "https://avatars3.githubusercontent.com/u/26?v=4",
        "https://avatars3.githubusercontent.com/u/27?v=4",
        "https://avatars2.githubusercontent.com/u/28?v=4",
        "https://avatars2.githubusercontent.com/u/29?v=4",
        "https://avatars2.githubusercontent.com/u/30?v=4",
        "https://avatars2.githubusercontent.com/u/31?v=4",
        "https://avatars2.githubusercontent.com/u/32?v=4",
        "https://avatars2.githubusercontent.com/u/34?v=4",
        "https://avatars2.githubusercontent.com/u/35?v=4",
        "https://avatars2.githubusercontent.com/u/36?v=4",
        "https://avatars2.githubusercontent.com/u/37?v=4",
        "https://avatars3.githubusercontent.com/u/38?v=4",
        "https://avatars2.githubusercontent.com/u/44?v=4",
        "https://avatars2.githubusercontent.com/u/45?v=4",
        "https://avatars2.githubusercontent.com/u/46?v=4"
    )
    val titleName = arrayListOf(
        "mojombo",
        "defunkt",
        "pjhyett",
        "wycats",
        "ezmobius",
        "ivey",
        "evanphx",
        "vanpelt",
        "wayneeseguin",
        "brynary",
        "kevinclark",
        "technoweenie",
        "macournoyer",
        "takeo",
        "caged",
        "topfunky",
        "anotherjesse",
        "roland",
        "lukas",
        "fanvsfan",
        "tomtt",
        "railsjitsu",
        "nitay",
        "kevwil",
        "KirinDave",
        "jamesgolick",
        "atmos",
        "errfree",
        "mojodna",
        "bmizerany"
    )
    val typeList = arrayListOf(
        "User",
        "Admin"
    )

    fun getRandomUser(): UserModel {
        val url = imageUrlList.random()
        val name = titleName.random()
        val usrType = typeList.random()

        return UserModel().apply {
            login = name
            type = usrType
            avatarUrl = url
        }

    }
}