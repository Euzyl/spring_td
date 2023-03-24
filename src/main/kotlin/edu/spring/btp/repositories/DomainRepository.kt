package edu.spring.btp.repositories

import edu.spring.btp.entities.Domain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DomainRepository:JpaRepository<Domain, Int> {
    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" ORDER BY rand() LIMIT 1")
    fun getRandomDomain(): Domain

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" INNER JOIN \"domain_providers\" ON \"providers_id\"=:providerId ORDER BY rand() LIMIT 1")
    fun getRandomDomainFromProvider(providerId:Int): Domain


    fun findByParentName(name:String):List<Domain>

    //@Query(nativeQuery = true, value = "SELECT * FROM \"domain\" WHERE \"parent_id\" =:id")
    fun findByParentId(id:Int):List<Domain>

    fun findByName(name:String):Domain

    fun findByParentIsNull():Domain

    @Query(nativeQuery = true, value = "SELECT * FROM \"domain\" WHERE \"parent_id\" = NULL")
    fun getDomainsWithoutParents():List<Domain>

    @Query(nativeQuery = true, value = "SELECT * FROM \"domain\" WHERE \"parent_id\"=:id" )
    fun getDomainsByParentId(id:Int):List<Domain>

    @Query(nativeQuery = true,value="SELECT * FROM \"domain\" WHERE \"parent_id\"=:parentId ORDER BY rand() LIMIT 1")
    fun getRandomDomainFromParentId(parentId:Int): Domain

    @Query(nativeQuery = true, value= "SELECT * FROM \"domain\" WHERE \"name\" = 'Root'")
    fun getRoot():Domain

}