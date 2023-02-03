package etu.spring.td2.models

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int? = null

    open lateinit var name: String
    //email, aliases, organization, users
    //user ->many to many
    //organization ->many to one
}