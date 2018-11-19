package it.tarczynski.kotlindemo.rabbit.service

import it.tarczynski.kotlindemo.rabbit.model.RabbitAge
import it.tarczynski.kotlindemo.rabbit.model.RabbitDto
import it.tarczynski.kotlindemo.rabbit.model.RabbitName
import it.tarczynski.kotlindemo.rabbit.model.RabbitSpecies

interface RabbitService {

    fun addRabbit(name: RabbitName, species: RabbitSpecies, age: RabbitAge): RabbitDto

    fun findBy(name: RabbitName): RabbitDto?

    fun findBy(species: RabbitSpecies): List<RabbitDto>

    fun findAllYoungerThen(age: RabbitAge): List<RabbitDto>

    fun findAll(): List<RabbitDto>
}
