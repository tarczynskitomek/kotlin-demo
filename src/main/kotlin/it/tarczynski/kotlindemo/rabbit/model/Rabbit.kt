package it.tarczynski.kotlindemo.rabbit.model

class Rabbit(val name: RabbitName,
             val species: RabbitSpecies,
             val age: RabbitAge) {

    fun toDto(): RabbitDto {
        return RabbitDto(name.value, species.value, age.value)
    }
}

data class RabbitDto(val name: String, val species: String, val age: Int)
