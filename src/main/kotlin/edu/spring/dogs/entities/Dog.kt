package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Dog() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0

    @Column(length = 30)
    open var name:String?=null

    constructor(name:String):
            this(){
                this.name=name
            }


    @ManyToOne
    open var master: Master?=null

    @OneToMany
    open val toys = mutableSetOf<Toy>()

}