package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Toy() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var idToy:Int = 0

    @Column(length = 30)
    open var type:String?=null

    @Column(length = 30)
    open var label:String=""



    constructor(t:String, l:String):
            this(){
                this.type=t
                this.label=l
            }

}