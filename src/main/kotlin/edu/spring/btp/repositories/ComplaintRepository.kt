package edu.spring.btp.repositories

import edu.spring.btp.entities.Complaint
import org.springframework.data.jpa.repository.JpaRepository

interface ComplaintRepository:JpaRepository<edu.spring.btp.entities.Complaint, Int> {
    fun findByDomainId(id:Int): List<Complaint>
}