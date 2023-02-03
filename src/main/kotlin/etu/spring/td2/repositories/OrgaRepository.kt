package etu.spring.td2.repositories

import etu.spring.td2.models.Organization

interface OrgaRepository: CRUDRepository<Organization, Int> { // ???
    fun findByDomain(domain: String): List<Organization>
    fun findByName(name: String): List<Organization>
    fun saveAndFlush(orga: Organization?) //c'est lui qui dit
}
