package etu.spring.td2.models

import jakarta.persistence.*

@Entity
//@Table(name = "utilisateur")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //≠ auto ?
    open var id: Int? = null //private int id;

    /*
    The “lateinit” keyword is used to declare those variables that are guaranteed
    to be initialized in the future. Properties of primitive data types
    as well as nullable properties, can't be declared using “lateinit”.
    late initialization
    */

    @Column(length = 255, nullable = false, unique = true)
    open lateinit var email: String

    @Column(length = 20)
    open lateinit var firstname: String
    @Column(length = 20, nullable = false, unique = true)
    open lateinit var lastname: String

    open var suspended=false

    //email, psw, suspended (boolean ?), organization, groupes

    @ManyToOne()
    @JoinColumn(name = "idOrganization", nullable = false)
    lateinit var  organization: Organization


    @ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name="user_groups")
    open val users = mutableSetOf<User>()

}