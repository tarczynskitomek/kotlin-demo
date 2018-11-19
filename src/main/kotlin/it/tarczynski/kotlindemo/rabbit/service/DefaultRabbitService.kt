package it.tarczynski.kotlindemo.rabbit.service

import it.tarczynski.kotlindemo.rabbit.execption.RabbitAlreadyExistsException
import it.tarczynski.kotlindemo.rabbit.model.*
import it.tarczynski.kotlindemo.rabbit.repository.RabbitRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DefaultRabbitService(private val rabbitRepository: RabbitRepository) : RabbitService {

    override fun addRabbit(name: RabbitName, species: RabbitSpecies, age: RabbitAge): RabbitDto {
        if (rabbitRepository.findBy(name) != null) {
            throw RabbitAlreadyExistsException()
        }
        return rabbitRepository
                .save(Rabbit(name, species, age))
                .toDto()
    }

    override fun findBy(name: RabbitName): RabbitDto? {
        return rabbitRepository.findBy(name)?.toDto()
    }

    override fun findBy(species: RabbitSpecies): List<RabbitDto> {
        return rabbitRepository.findBy(species)
                .map { it.toDto() }
    }

    override fun findAllYoungerThen(age: RabbitAge): List<RabbitDto> {
        return rabbitRepository
                .findAllYoungerThen(age)
                .map { it.toDto() }
    }
}
