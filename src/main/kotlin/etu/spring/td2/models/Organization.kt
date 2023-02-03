package etu.spring.td2.models

import jakarta.persistence.*


@Entity
open class Organization {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy=GenerationType.AUTO)
    open var id: Int? = null

    open lateinit var name: String
    open var domain: String? = null
    open var aliases: String? = null

    /*
    private String name;
    private String domain;
    private String aliases;
    */

   // @OneToMany(cascade=CascadeType.ALL,mappedBy="organization") >:(
    //private Set<Groupe> groupes;
   // private lateinit var groupes:Set<Group>



}