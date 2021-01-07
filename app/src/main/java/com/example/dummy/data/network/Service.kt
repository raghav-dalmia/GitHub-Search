package com.example.dummy.data.network

import com.example.dummy.data.network.model.UserMeta
import com.example.dummy.data.network.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service{

    @GET("search/users")
    fun getUsers(
        @Query("q") userName : String,
        @Query("per_page") numUserPerPage : Int,
        @Query("page") pageNumber : Int
    ) : Call<UserResponse>

    @GET("search/users/{username}")
    fun getUserMeta(
        @Path("username") userName: String
    ) : Call<UserMeta>

}