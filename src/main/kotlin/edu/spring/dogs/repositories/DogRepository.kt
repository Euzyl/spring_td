package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DogRepository: CrudRepository<Dog,Int> {
    public fun findByMasterIsNull() : List<Dog>
    public fun findByNameAndMasterId(name:String, id:Int) : Dog
}