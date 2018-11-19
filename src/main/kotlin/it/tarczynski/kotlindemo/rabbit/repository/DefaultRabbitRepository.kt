package it.tarczynski.kotlindemo.rabbit.repository

import it.tarczynski.kotlindemo.rabbit.model.Rabbit
import it.tarczynski.kotlindemo.rabbit.model.RabbitAge
import it.tarczynski.kotlindemo.rabbit.model.RabbitName
import it.tarczynski.kotlindemo.rabbit.model.RabbitSpecies
import org.springframework.stereotype.Repository

@Repository
class DefaultRabbitRepository
internal constructor(private val internalRepository: InternalRabbitRepository) : RabbitRepository {

    override fun save(rabbit: Rabbit): Rabbit {
        return internalRepository.save(rabbit.toData()).toModel()
    }

    override fun findAll(): List<Rabbit> {
        return internalRepository.findAll().map { it.toModel() }
    }

    override fun findBy(name: RabbitName): Rabbit? {
        return internalRepository.findByName(name.value)?.toModel()
    }

    override fun findBy(species: RabbitSpecies): List<Rabbit> {
        return internalRepository.findBySpecies(species.value).map { it.toModel() }
    }

    override fun findAllYoungerThen(age: RabbitAge): List<Rabbit> {
        return internalRepository.findAllYoungerThen(age.value).map { it.toModel() }
    }
}

internal fun Rabbit.toData(id: Long = 0): RabbitData {
    return RabbitData(id, name.value, species.value, age.value)
}
