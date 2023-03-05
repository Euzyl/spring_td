package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Toy
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ToyRepository: CrudRepository<Toy, Int> {
    //recherche par type (type:Type)
    public fun findByType(type:String) : List<Toy>
}