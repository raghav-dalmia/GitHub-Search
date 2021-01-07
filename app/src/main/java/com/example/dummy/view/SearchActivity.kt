package com.example.dummy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.dummy.R
import com.example.dummy.data.adapters.UserAdapter
import com.example.dummy.viewModel.SearchActivityViewModel

class SearchActivity : AppCompatActivity() {

    lateinit var viewModel: SearchActivityViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        val searchButton = findViewById<AppCompatImageView>(R.id.button_search)
        val searchText = findViewById<AppCompatEditText>(R.id.edit_text_search)
        val userListRV = findViewById<RecyclerView>(R.id.search_result_list)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        searchButton.setOnClickListener {
            if(searchText.text!!.isNotEmpty()) {
                viewModel.searchUser(searchText.text.toString())
            }
        }

        viewModel.showProgressBar.observe(this, Observer {
            if(it)
                progressBar.visibility = VISIBLE
            else
                progressBar.visibility = INVISIBLE
        })

        viewModel.userList.observe(this, Observer {
            adapter.setUserList(it)
        })
        adapter = UserAdapter(this)
        userListRV.adapter = adapter
    }
}