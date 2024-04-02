package dev.norman.dogdex.api.responses

import com.google.gson.annotations.SerializedName

class SignUpApiResponse(
    val message: String,
    @SerializedName("is_success") val isSuccess: Boolean,
    val data: UserResponse,
)