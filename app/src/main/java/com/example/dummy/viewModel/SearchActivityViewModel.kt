package com.example.dummy.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dummy.data.network.model.User
import com.example.dummy.data.network.model.UserMeta
import com.example.dummy.repository.SearchActivityRepository

class SearchActivityViewModel(application: Application): AndroidViewModel(application) {

    private val repository = SearchActivityRepository(application)
    val userList : LiveData<List<User>>
    val showProgressBar : LiveData<Boolean>


    init{
        this.userList = repository.userList
        this.showProgressBar = repository.showProgressBar
    }

    fun searchUser( username : String){
        repository.searchUser(username)
    }

    fun getUserMeta(username: String): UserMeta? {
        return repository.getUserMeta(username)
    }

}