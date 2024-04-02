package dev.norman.dogdex.api.dto

import dev.norman.dogdex.model.Dog

class DogDTOMapper {
    private fun fromDogDTOtoDogDomain(dogDTO: DogDTO): Dog {
        return Dog(
            dogDTO.id,
            dogDTO.index,
            dogDTO.name,
            dogDTO.type,
            dogDTO.heightFemale,
            dogDTO.heightMale,
            dogDTO.imageUrl,
            dogDTO.lifeExpectancy,
            dogDTO.temperament,
            dogDTO.weightFemale,
            dogDTO.weightMale
        )
    }

    fun fromDogDTOListToDogDomainList(dogDtoList: List<DogDTO>): List<Dog> {
        return dogDtoList.map {
            fromDogDTOtoDogDomain(it)
        }
    }
}