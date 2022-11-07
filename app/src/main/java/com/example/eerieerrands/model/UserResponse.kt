package com.example.eerieerrands.model

data class UserResponse (
    val uuid: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val enabled: Boolean,
    val jwt: String?
)
