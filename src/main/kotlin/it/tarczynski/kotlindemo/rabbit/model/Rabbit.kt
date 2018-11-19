package it.tarczynski.kotlindemo.rabbit.model

import javax.persistence.*

@Entity
@Table(name = "rabbits", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
class Rabbit(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @Column(nullable = false)
        val name: RabbitName,

        @Column(nullable = false)
        val species: RabbitSpecies,

        @Column(nullable = false)
        val age: RabbitAge) {

    fun toDto(): RabbitDto {
        return RabbitDto(name.value, species.value, age.value)
    }
}

data class RabbitDto(val name: String,
                     val species: String,
                     val age: Int)
