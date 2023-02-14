package etu.spring.td2.models

import jakarta.persistence.*

@Entity
open class Organization() {
    fun addUser(user: User) {
        if(users.add(user)){
            user.organization=this
        }
    }
    //open obligatoire !!
    constructor(name:String):this(){
        this.name=name
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    open var id: Int? = null

    @Column(length = 60, nullable = false, unique = true)
    open lateinit var name: String
    @Column(length = 20)
    open var domain: String? = ""
    @Column(length = 20)
    open var aliases: String? = ""

// jdbc.h2./data/messagerie  8080/h2-console

    @OneToMany(mappedBy="organization", fetch=FetchType.EAGER, cascade=[CascadeType.ALL])
    open val users = mutableSetOf<User>()



    @OneToMany
    open val groups = mutableSetOf<Group>()

    fun addGroup(group:Group){
        if(groups.add(group)){
            group.organisation=this
        }
    }
}