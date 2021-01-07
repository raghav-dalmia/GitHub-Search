package com.example.dummy.repository

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dummy.BASE_URL
import com.example.dummy.data.network.Service
import com.example.dummy.data.network.model.User
import com.example.dummy.data.network.model.UserMeta
import com.example.dummy.data.network.model.UserResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository(val application: Application) {

    val userList = MutableLiveData<List<User>>()
    var showProgressBar = MutableLiveData<Boolean>()
    private val service = initRetrofit()

    fun searchUser( username : String){
        showProgressBar.value = true
        service.getUsers(username, 50,1).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                userList.value = response.body()?.items
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(application, "Error occur!\nPlease try again", Toast.LENGTH_LONG).show()
            }
        })
        showProgressBar.value = false
    }

    fun getUserMeta(username: String): UserMeta? {
        var userMeta : UserMeta? = null
        service.getUserMeta(username).enqueue(object : Callback<UserMeta>{
            override fun onResponse(call: Call<UserMeta>, response: Response<UserMeta>) {
                userMeta = response.body()!!
            }

            override fun onFailure(call: Call<UserMeta>, t: Throwable) {
                Toast.makeText(application, "Error occur!\nPlease try again", Toast.LENGTH_LONG)
                    .show()
            }
        })
        return userMeta
    }

    private fun initRetrofit(): Service {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(Service::class.java)
    }

}