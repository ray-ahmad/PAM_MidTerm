package com.rayahmad.pammidterm.model

data class UserResponse(
    val data: List<UsersModel>
)

data class UsersModel(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)
