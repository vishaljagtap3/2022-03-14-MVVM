package com.bitcode.mvvm1.models

import com.google.gson.annotations.SerializedName

data class UsersResponseModel(
    @SerializedName("page")
    val pageNumber : Int,

    @SerializedName("per_page")
    val itemsPerPage : Int,

    val total : Int,

    @SerializedName("total_pages")
    val  totalPages: Int,

    @SerializedName("data")
    var users : ArrayList<UserModel>
)