package etu.spring.td2.repositories

import etu.spring.td2.models.Group
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GrpRepository : CrudRepository<Group, Int> {
}