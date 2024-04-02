package dev.norman.dogdex.auth

import dev.norman.dogdex.api.ApiResponseStatus
import dev.norman.dogdex.api.DogsApi
import dev.norman.dogdex.api.dto.DogDTOMapper
import dev.norman.dogdex.api.dto.SignUpDTO
import dev.norman.dogdex.api.makeNetworkCall
import dev.norman.dogdex.model.User

class AuthRepository {

    suspend fun signUp(email: String, password: String,
                       passwordConfirmation: String): ApiResponseStatus<User> = makeNetworkCall {
        val signUpDTO = SignUpDTO(email, password, passwordConfirmation)
        val signUpResponse = DogsApi.retrofitService.signUp(signUpDTO)
        val userDTO = signUpResponse.data.user
        val dogDTOMapper = DogDTOMapper()
        dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
    }
}