package dev.norman.dogdex.api.dto

import com.google.gson.annotations.SerializedName

class UserDTO(
    val id: Long,
    val email: String,
    @SerializedName("authentication_token") val authenticationToken: String,
)
