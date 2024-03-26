package dev.norman.dogdex

data class Dog(
    val id: Long,
    val index: Int,
    val name: String,
    val type: String,
    val heightFemale: Double,
    val heightMale: Double,
    val imageUrl: String,
    val lifeExpectancy: String,
    val temperament: String,
    val weightFemale: Double,
    val weightMale: Double
)


fun getFakeDogs(): MutableList<Dog> {
    val dogList = mutableListOf<Dog>()
    dogList.add(
        Dog(
            1, 1, "Chihuahua", "Toy", 5.4,
            6.7, "", "12 - 15", "", 10.5,
            12.3
        )
    )
    dogList.add(
        Dog(
            2, 1, "Labrador", "Toy", 5.4,
            6.7, "", "12 - 15", "", 10.5,
            12.3
        )
    )
    dogList.add(
        Dog(
            3, 1, "Retriever", "Toy", 5.4,
            6.7, "", "12 - 15", "", 10.5,
            12.3
        )
    )
    dogList.add(
        Dog(
            4, 1, "San Bernardo", "Toy", 5.4,
            6.7, "", "12 - 15", "", 10.5,
            12.3
        )
    )
    dogList.add(
        Dog(
            5, 1, "Husky", "Toy", 5.4,
            6.7, "", "12 - 15", "", 10.5,
            12.3
        )
    )
    dogList.add(
        Dog(
            6, 1, "Xoloscuincle", "Toy", 5.4,
            6.7, "", "12 - 15", "", 10.5,
            12.3
        )
    )
    return dogList
}