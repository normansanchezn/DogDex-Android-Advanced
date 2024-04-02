package dev.norman.dogdex.auth

import dev.norman.dogdex.api.ApiResponseStatus
import dev.norman.dogdex.api.DogsApi
import dev.norman.dogdex.api.dto.LoginDTO
import dev.norman.dogdex.api.dto.SignUpDTO
import dev.norman.dogdex.api.dto.UserDTOMapper
import dev.norman.dogdex.api.makeNetworkCall
import dev.norman.dogdex.model.User

class AuthRepository {

    suspend fun signUp(email: String, password: String,
                       passwordConfirmation: String): ApiResponseStatus<User> = makeNetworkCall {
        val signUpDTO = SignUpDTO(email, password, passwordConfirmation)
        val signUpResponse = DogsApi.retrofitService.signUp(signUpDTO)

        if (!signUpResponse.isSuccess) {
            throw Exception(signUpResponse.message)
        }

        val userDTO = signUpResponse.data.user
        val userDTOMapper = UserDTOMapper()
        userDTOMapper.fromUserDTOToUserDomain(userDTO)
    }

    suspend fun login(email: String, password: String): ApiResponseStatus<User> = makeNetworkCall {
        val loginDTO = LoginDTO(email, password)
        val loginResponse = DogsApi.retrofitService.login(loginDTO)

        if (!loginResponse.isSuccess) {
            throw Exception(loginResponse.message)
        }

        val userDTO = loginResponse.data.user
        val userDTOMapper = UserDTOMapper()
        userDTOMapper.fromUserDTOToUserDomain(userDTO)
    }
}