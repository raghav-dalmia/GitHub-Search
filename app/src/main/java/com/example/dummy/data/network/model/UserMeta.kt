package com.example.dummy.data.network.model

data class UserMeta(
    val followers: Int,
    val following: Int,
    val location: String,
    val name: String,
    val public_gists: Int,
    val public_repos: Int
)