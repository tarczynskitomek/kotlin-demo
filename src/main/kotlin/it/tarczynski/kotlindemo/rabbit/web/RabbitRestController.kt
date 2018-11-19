package it.tarczynski.kotlindemo.rabbit.web

import it.tarczynski.kotlindemo.rabbit.execption.RabbitAlreadyExistsException
import it.tarczynski.kotlindemo.rabbit.model.RabbitAge
import it.tarczynski.kotlindemo.rabbit.model.RabbitDto
import it.tarczynski.kotlindemo.rabbit.model.RabbitName
import it.tarczynski.kotlindemo.rabbit.model.RabbitSpecies
import it.tarczynski.kotlindemo.rabbit.service.RabbitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/rabbits")
class RabbitRestController(private val rabbitService: RabbitService) {

    @GetMapping
    fun getAllRabbits(): List<RabbitDto> {
        return rabbitService.findAll()
    }

    @GetMapping("/{name}")
    fun getRabbitByName(@PathVariable name: String): ResponseEntity<RabbitDto> {
        return rabbitService
                .findBy(RabbitName(name))
                ?.let { ResponseEntity.ok(it) }
                ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createRabbit(@RequestBody rabbit: RabbitDto): ResponseEntity<RabbitDto> {
        return try {
            val newRabbit = rabbitService.addRabbit(
                    RabbitName(rabbit.name),
                    RabbitSpecies(rabbit.species),
                    RabbitAge(rabbit.age))

            ResponseEntity
                    .created(URI("/api/rabbits/${newRabbit.name}"))
                    .body(newRabbit)
        } catch (e: RabbitAlreadyExistsException) {
            ResponseEntity
                    .status(HttpStatus.TOO_MANY_REQUESTS)
                    .build()
        }
    }
}
