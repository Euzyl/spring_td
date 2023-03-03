package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Dog() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var idDog:Int = 0

    @Column(length = 30)
    open var name:String?=null

    constructor(name:String):
            this(){
                this.name=name
            }

    @OneToOne
    @JoinColumn(name="idMaster", nullable = true)
    open var master: Master?=null

    @OneToMany
    @JoinColumn(name="idToy", nullable = true)
    open val toys = mutableSetOf<Toy>()

}