package com.example.eerieerrands.model

data class ChildEntity(
        val uuid: String,
        val userId: String,
        val firstName: String,
        val lastName: String,
        val pendingPayout: Int,
        val lifetimePayout: Int,
)
