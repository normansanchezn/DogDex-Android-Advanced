package dev.norman.dogdex.api.responses

import com.google.gson.annotations.SerializedName

data class DogListApiResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("is_success")
    val isSuccess: Boolean,
    @SerializedName("data")
    val data: DogListResponse
)