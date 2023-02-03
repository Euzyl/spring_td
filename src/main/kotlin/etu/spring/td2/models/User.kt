package etu.spring.td2.models

import jakarta.persistence.*

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //≠ auto ?
    open var id: Int? = null //private int id;

    /*
    The “lateinit” keyword is used to declare those variables that are guaranteed
    to be initialized in the future. Properties of primitive data types
    as well as nullable properties, can't be declared using “lateinit”.
    late initialization
    */

    open lateinit var firstname: String
    open lateinit var lastname: String

    //open ?

    //email, psw, suspended (boolean ?), organization, groupes
    //group -> many to many
    //organization -> many to one

    @ManyToOne
    private lateinit var  organization: Organization;

    @ManyToMany(mappedBy="users")
    //private Set<Group> groupes;
    private lateinit var groupes:Set<Group>


}