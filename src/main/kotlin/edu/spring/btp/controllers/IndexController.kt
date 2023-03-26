package edu.spring.btp.controllers

import edu.spring.btp.repositories.DomainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*


@Controller
class IndexController {
    @Autowired
    lateinit var userRepository: edu.spring.btp.repositories.UserRepository

    @Autowired
    lateinit var domainRepository: DomainRepository

    @Autowired
    lateinit var complaintRepository: edu.spring.btp.repositories.ComplaintRepository

    @Autowired
    lateinit var providerRepository: edu.spring.btp.repositories.ProviderRepository

    @RequestMapping("/","/index")
    fun index(model: ModelMap, auth: Neo4jProperties.Authentication): String {
        val children = domainRepository.findByParentId(1)
        //val domain = domainRepository.getRandomDomain()
        val root = domainRepository.getRoot()
        model["domain"] = root
        model["children"]= children
        //model["hasChildren"] = children.count()>0
        model["user"] = auth
        return "index"
    }

    @GetMapping("/domain/{name}")
    fun domainChilren(model: ModelMap, @PathVariable name:String) : String{
        model["domain"] = domainRepository.findByName(name)
        model["children"] = domainRepository.findByParentId(domainRepository.findByName(name).id)

        return "domain"
    }

    @PostMapping("/signup")
    fun signupAction(model: ModelMap){

    }

    @PostMapping("/login")
    fun processLogin(@RequestParam username: String, @RequestParam password: String, model: ModelMap): String? {
        // Vérifiez les informations de connexion de l'utilisateur ici
        return if (username == "admin" && password == "password") {
            // Si les informations de connexion sont valides, transmettez le nom d'utilisateur à la vue
            model.addAttribute("username", username)
            // Redirigez l'utilisateur vers la page d'accueil de votre application
            "redirect:/"
        } else {
            // Si les informations de connexion sont invalides, affichez un message d'erreur dans la vue
            model.addAttribute("error", "Invalid username or password")
            "login"
        }
    }


}