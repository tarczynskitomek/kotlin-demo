package it.tarczynski.kotlindemo.rabbit.repository

import it.tarczynski.kotlindemo.rabbit.model.Rabbit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface RabbitRepository : JpaRepository<Rabbit, Long> {

    fun findByName(name: String): Optional<Rabbit>

    fun findBySpecies(species: String): List<Rabbit>

    @Query("SELECT r FROM Rabbit r WHERE r.age < :age")
    fun findAllYoungerThen(age: Int): List<Rabbit>
}
