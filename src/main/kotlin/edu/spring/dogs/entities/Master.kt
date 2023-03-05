package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
open class Master() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0

    @Column(length = 30)
    open var firstname:String?=null

    @Column(length = 30)
    open var lastname:String?=null

    constructor(firstname:String, lastname:String):
            this(){
                this.firstname=firstname
                this.lastname=lastname
            }


    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    open val dogs = mutableSetOf<Dog>()

    fun addDog(dog: Dog) {
        if(dogs.add(dog)){
            dog.master=this
        }
    }

    fun giveUpDog(dog: Dog){
        if(dogs.remove(dog)){
            dog.master=null
        }

    }
/*
    if(dogs.remove(dog)){
        dog.master=null
    }
    dog.forEach{
        if(dogs.remove(it)){
            it.master=null
        }
    }
*/

    @PreRemove
    fun preRemove(){
        dogs.forEach { it.master = null }
    }
}