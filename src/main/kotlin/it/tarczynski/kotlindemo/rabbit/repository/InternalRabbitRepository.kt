package it.tarczynski.kotlindemo.rabbit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

internal interface InternalRabbitRepository : JpaRepository<RabbitData, Long> {

    fun findByName(name: String): RabbitData?

    fun findBySpecies(species: String): List<RabbitData>

    @Query("SELECT r FROM RabbitData r WHERE r.age < :age")
    fun findAllYoungerThen(age: Int): List<RabbitData>
}
