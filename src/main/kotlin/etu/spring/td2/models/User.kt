package etu.spring.td2.models

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Int? = null

    /*
    The “lateinit” keyword is used to declare those variables that are guaranteed
    to be initialized in the future. Properties of primitive data types
    as well as nullable properties, can't be declared using “lateinit”.
     */

    open lateinit var firstname: String
    open lateinit var lastname: String

    //email, psw, suspended (boolean ?), organization, groupes
    //group -> many to many
    //organization -> many to one

}