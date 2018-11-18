package it.tarczynski.kotlindemo.rabbit.service

import it.tarczynski.kotlindemo.rabbit.model.RabbitDto

interface RabbitService {

    fun addRabbit(name: String, species: String, age: Int): RabbitDto

    fun findByName(name: String): RabbitDto?

    fun findBySpecies(species: String): List<RabbitDto>

    fun findAllYoungerThen(age: Int): List<RabbitDto>
}