package edu.spring.dogs.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
open class Role() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int = 0
}