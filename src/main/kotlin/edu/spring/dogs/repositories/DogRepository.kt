package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Dog
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DogRepository: CrudRepository<Dog,Int> {
    //recherche par type (type:Type)

}