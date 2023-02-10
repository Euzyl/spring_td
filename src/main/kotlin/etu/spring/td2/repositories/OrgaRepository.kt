package etu.spring.td2.repositories

import etu.spring.td2.models.Organization
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrgaRepository: CrudRepository<Organization, Int> {
    //crud = opérations de base pr lecture, maj, suppr (create/delete/update) int = type identifiant
    fun findByDomain(domain: String): List<Organization>
    fun findByName(name: String): List<Organization>
    fun saveAndFlush(orgas: Organization?) //c'est lui qui dit
}
