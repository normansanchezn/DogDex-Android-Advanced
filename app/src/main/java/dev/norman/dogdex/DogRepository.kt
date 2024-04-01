package dev.norman.dogdex

import dev.norman.dogdex.api.ApiResponseStatus
import dev.norman.dogdex.api.DogsApi.retrofitService
import dev.norman.dogdex.api.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class DogRepository {

    suspend fun downloadDogs(): ApiResponseStatus {
        return withContext(Dispatchers.IO) {

            try {
                val dogListApiResponse = retrofitService.getAllDogs()
                val dogDtoList = dogListApiResponse.data.dogs
                val dogDtoMapper = DogDTOMapper()

                ApiResponseStatus.Success(dogDtoMapper.fromDogDTOListToDogDomainList(dogDtoList))
            } catch (e: UnknownHostException) {
                ApiResponseStatus.Error(R.string.unknown_host_exception_error)
            } catch (e: Exception) {
                ApiResponseStatus.Error(R.string.unknown_error)
            }
        }
    }
}