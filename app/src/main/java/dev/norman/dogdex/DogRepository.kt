package dev.norman.dogdex

import dev.norman.dogdex.api.DogsApi.retrofitService
import dev.norman.dogdex.api.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository {

    suspend fun downloadDogs(): List<Dog> {
        return withContext(Dispatchers.IO) {
            val dogListApiResponse = retrofitService.getAllDogs()
            val dogDtoList = dogListApiResponse.data.dogs
            val dogDtoMapper = DogDTOMapper()
            dogDtoMapper.fromDogDTOListToDogDomainList(dogDtoList)
        }
    }
}