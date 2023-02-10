package etu.spring.td2.repositories

import etu.spring.td2.models.Organization
import org.springframework.data.repository.CrudRepository

interface OrgaRepository: CrudRepository<Organization, Int> { // ???
    fun findByDomain(domain: String): List<Organization>
    fun findByName(name: String): List<Organization>
    fun saveAndFlush(orga: Organization?) //c'est lui qui dit
}
