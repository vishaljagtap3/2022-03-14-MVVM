package com.bitcode.mvvm1.network

import com.bitcode.mvvm1.models.UserPostModel
import com.bitcode.mvvm1.models.UserPostResponseModel
import com.bitcode.mvvm1.models.UsersResponseModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @GET("users?")
    suspend fun getUsers(
        @Query("page") pageNumber : Int
    ) : UsersResponseModel

    @FormUrlEncoded
    @POST("users")
    suspend fun addUser(
        @Field("name") name : String,
        @Field("job") job : String
    ) : UserPostResponseModel

    @POST("users")
    suspend fun addUserObject(
        @Body user : UserPostModel
    ) : UserPostResponseModel

    companion object {
        var apiService : ApiService? = null

        fun getInstance() : ApiService {
            if(apiService == null) {
                var retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}