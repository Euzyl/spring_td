package etu.spring.td2.models

import jakarta.persistence.*

@Entity
class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    open var id: Int? = null

    open lateinit var name: String
    //email, aliases, organization, users

    //user ->many to many
    //organization ->many to one

    @ManyToOne
    @JoinColumn(name="idOrganization", nullable=false)
    private lateinit var organisation:Organization

    @ManyToOne
    private lateinit var organization: Organization
    @ManyToMany
    @JoinTable(name = "user_group")
    private lateinit var users:Set<User>


}