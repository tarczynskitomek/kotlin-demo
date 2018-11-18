package it.tarczynski.kotlindemo.rabbit.model

import javax.persistence.*

@Entity
@Table(name = "rabbits", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
class Rabbit(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = false)
        val species: String,

        @Column(nullable = false)
        val age: Int) {

    fun toDto(): RabbitDto {
        return RabbitDto(name, species, age)
    }
}

data class RabbitDto(val name: String,
                     val species: String,
                     val age: Int)
