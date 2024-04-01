package dev.norman.dogdex

import dev.norman.dogdex.api.ApiResponseStatus
import dev.norman.dogdex.api.DogsApi.retrofitService
import dev.norman.dogdex.api.dto.DogDTOMapper
import dev.norman.dogdex.api.makeNetworkCall

class DogRepository {

    suspend fun downloadDogs(): ApiResponseStatus<List<Dog>> = makeNetworkCall {
        val dogListApiResponse = retrofitService.getAllDogs()
        val dogDtoList = dogListApiResponse.data.dogs
        val dogDtoMapper = DogDTOMapper()

        dogDtoMapper.fromDogDTOListToDogDomainList(dogDtoList)
    }
}