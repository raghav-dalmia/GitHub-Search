package com.example.dummy.data.network.model

data class UserResponse(
    val incomplete_results: Boolean,
    val items: List<User>,
    val total_count: Int
)