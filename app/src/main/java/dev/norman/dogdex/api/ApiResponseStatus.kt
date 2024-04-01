package dev.norman.dogdex.api

import dev.norman.dogdex.Dog

sealed class ApiResponseStatus() {
    class Success(val dogList: List<Dog>): ApiResponseStatus()
    class Error(val messageId: Int): ApiResponseStatus()
    data object Loading : ApiResponseStatus()
}