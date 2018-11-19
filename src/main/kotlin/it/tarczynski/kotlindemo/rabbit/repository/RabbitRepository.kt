package it.tarczynski.kotlindemo.rabbit.repository

import it.tarczynski.kotlindemo.rabbit.model.Rabbit
import it.tarczynski.kotlindemo.rabbit.model.RabbitAge
import it.tarczynski.kotlindemo.rabbit.model.RabbitName
import it.tarczynski.kotlindemo.rabbit.model.RabbitSpecies

interface RabbitRepository {

    fun save(rabbit: Rabbit): Rabbit

    fun findAll(): List<Rabbit>

    fun findBy(name: RabbitName): Rabbit?

    fun findBy(species: RabbitSpecies): List<Rabbit>

    fun findAllYoungerThen(age: RabbitAge): List<Rabbit>
}
