package com.setiadev.tango.model

data class Player(
    val id: Long,
    val image: Int,
    val name: String,
    val jerseyNumber: Int,
    val description: String,
    val born: String,
    val team: String
)
