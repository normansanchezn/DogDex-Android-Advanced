package dev.norman.dogdex.api.dto

import com.google.gson.annotations.SerializedName

class SignUpDTO(
    val email: String,
    val password: String,
    @SerializedName("password_confirmation") val passwordConfirmation: String
)