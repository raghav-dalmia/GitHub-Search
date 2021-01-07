package com.example.dummy.data.network.model

data class User(
    val avatar_url: String,
    val login: String,
    val html_url: String,
    val url: String,
    val userMeta: UserMeta? = null
)