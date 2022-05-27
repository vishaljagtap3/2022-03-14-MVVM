package com.bitcode.mvvm1.respository

import com.bitcode.mvvm1.models.UsersResponseModel
import com.bitcode.mvvm1.network.ApiService

class UsersRepository(val apiService: ApiService) {

    suspend fun getUsersByPage(pageNumber : Int) : UsersResponseModel {
        return apiService.getUsers(pageNumber)
    }

}