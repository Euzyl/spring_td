package etu.spring.td2.models

import jakarta.persistence.*

@Entity
open class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int? = null

    open lateinit var name: String
    open var domain: String? = null
    open var aliases: String? = null
}