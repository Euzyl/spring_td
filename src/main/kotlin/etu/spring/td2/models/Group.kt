package etu.spring.td2.models

import jakarta.persistence.*

@Entity
//@Table(name = "groupe")
open class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    open var id: Int? = null

    @Column(length = 20, nullable = false, unique = true)
    open lateinit var name: String
    @Column(length = 20)
    open var email :String? = null
    @Column(length = 20)
    open var aliases :String? = null

    // organization, users

    //user ->many to many
    //organization ->many to one
/*
    @ManyToOne
    @JoinColumn(name="idOrganization", nullable=false)
    private lateinit var organisation:Organization

    @ManyToOne
    private lateinit var organization: Organization
    @ManyToMany
    @JoinTable(name = "user_group")
    private lateinit var users:Set<User>
*/

}
