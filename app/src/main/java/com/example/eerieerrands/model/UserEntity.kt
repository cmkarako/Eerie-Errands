package com.example.eerieerrands.model

data class UserEntity(
    val uuid: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val enabled: Boolean,
)