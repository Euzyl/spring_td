package etu.spring.td2.services

import etu.spring.td2.models.Group
import etu.spring.td2.models.Organization
import etu.spring.td2.models.User
import org.springframework.stereotype.Service

@Service
class OrgaService {
    fun addUsersToOrga(orga:Organization, users:String){
        if(users.isNotEmpty()){
            users.split(",").forEach(){
                val user = User()
                val (firstname, lastname)=it.trim().split(" ", limit = 2)
                user.firstname=firstname
                user.lastname=lastname
                user.email="$firstname.$lastname@${orga.domain}".lowercase()
                orga.addUser(user)
            }
        }
    }

    fun addGroupsToOrga(orga:Organization, groups:String){
        if(groups.isNotEmpty()){
            groups.split(",").forEach(){
                val group=Group()
                group.name=it.trim()
                orga.addGroup(group)

            }
        }
    }
}