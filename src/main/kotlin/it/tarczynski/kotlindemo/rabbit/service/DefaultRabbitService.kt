package it.tarczynski.kotlindemo.rabbit.service

import it.tarczynski.kotlindemo.rabbit.execption.RabbitAlreadyExistsException
import it.tarczynski.kotlindemo.rabbit.model.Rabbit
import it.tarczynski.kotlindemo.rabbit.model.RabbitDto
import it.tarczynski.kotlindemo.rabbit.repository.RabbitRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DefaultRabbitService(private val rabbitRepository: RabbitRepository) : RabbitService {

    override fun addRabbit(name: String, species: String, age: Int): RabbitDto {
        if (rabbitRepository.findByName(name).isPresent) {
            throw RabbitAlreadyExistsException()
        }
        return rabbitRepository.save(Rabbit(0, name, species, age)).toDto()
    }

    override fun findByName(name: String): RabbitDto? {
        return rabbitRepository.findByName(name)
                .map { it.toDto() }
                .orElse(null)
    }

    override fun findBySpecies(species: String): List<RabbitDto> {
        return rabbitRepository.findBySpecies(species).map { it.toDto() }
    }

    override fun findAllYoungerThen(age: Int): List<RabbitDto> {
        return rabbitRepository.findAllYoungerThen(age).map { it.toDto() }
    }
}
