package dev.norman.dogdex.api.responses

import com.google.gson.annotations.SerializedName
import dev.norman.dogdex.api.dto.DogDTO

data class DogListResponse(
    @SerializedName("dogs")
    val dogs: List<DogDTO>
)