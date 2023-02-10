package etu.spring.td2.repositories

import etu.spring.td2.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsrRepository: CrudRepository<User,Int> {
}