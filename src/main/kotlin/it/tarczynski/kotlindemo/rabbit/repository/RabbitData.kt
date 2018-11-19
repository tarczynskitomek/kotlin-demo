package it.tarczynski.kotlindemo.rabbit.repository

import it.tarczynski.kotlindemo.rabbit.model.Rabbit
import it.tarczynski.kotlindemo.rabbit.model.RabbitAge
import it.tarczynski.kotlindemo.rabbit.model.RabbitName
import it.tarczynski.kotlindemo.rabbit.model.RabbitSpecies
import javax.persistence.*

@Entity
@Table(name = "rabbits", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
internal class RabbitData(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @Column(nullable = false)
        val name: String,

        @Column(nullable = false)
        val species: String,

        @Column(nullable = false)
        val age: Int
) {

        fun toModel(): Rabbit {
                return Rabbit(RabbitName(name), RabbitSpecies(species), RabbitAge(age))
        }
}
