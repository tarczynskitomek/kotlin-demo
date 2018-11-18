package it.tarczynski.kotlindemo.rabbit.web

import it.tarczynski.kotlindemo.rabbit.execption.RabbitAlreadyExistsException
import it.tarczynski.kotlindemo.rabbit.model.RabbitDto
import it.tarczynski.kotlindemo.rabbit.service.RabbitService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/rabbits")
class RabbitRestController(private val rabbitService: RabbitService) {

    @GetMapping("/{name}")
    fun getRabbitByName(@PathVariable name: String): ResponseEntity<RabbitDto> {
        return rabbitService
                .findByName(name)
                ?.let { ResponseEntity.ok(it) }
                ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createRabbit(@RequestBody rabbit: RabbitDto): ResponseEntity<RabbitDto> {
        return try {
            val newRabbit = rabbitService.addRabbit(rabbit.name, rabbit.species, rabbit.age)
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
