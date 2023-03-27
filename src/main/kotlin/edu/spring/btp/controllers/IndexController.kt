package edu.spring.btp.controllers

import DbUserService
import edu.spring.btp.entities.User
import edu.spring.btp.repositories.DomainRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.security.Principal


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

    @Autowired
    lateinit var dbUserService: UserDetailsService

    @RequestMapping("/","/index")
    fun index(model: ModelMap, auth: Authentication?): String { //
        val children = domainRepository.findByParentId(1)
        //val domain = domainRepository.getRandomDomain()
        val root = domainRepository.getRoot()
        model["domain"] = root
        model["children"]= children
        //model["hasChildren"] = children.count()>0
        if(auth != null) model["user"] = auth
        return "index"
    }

    @GetMapping("/domain/{name}")
    fun domainChilren(model: ModelMap, @PathVariable name:String, auth: Authentication?) : String{
        model["domain"] = domainRepository.findByName(name)
        model["children"] = domainRepository.findByParentId(domainRepository.findByName(name).id)
        if(auth != null) model["user"] = auth
        return "domain"
    }

    /*
    @PostMapping("/signup")
    fun createUser(model: ModelMap):String{
            val user= User()
            user.username=username
            user.email=username.lowercase()+"@gmail.com"
            user.role="USER"
            user.password=password
            (dbUserService as DbUserService).encodePassword(user)
            userRepository.save(user)
            return "redirect:/"

    }

     */
/*
    @GetMapping("/login")
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

 */

    @GetMapping("/logout")
    fun processLogout(request: HttpServletRequest, response: HttpServletResponse?): String? {
        // Effacez toutes les informations de session associées à l'utilisateur
        val session = request.getSession(false)
        SecurityContextHolder.clearContext()
        session?.invalidate()
        // Redirigez l'utilisateur vers la page de connexion
        return "redirect:/login"
    }


    @RequestMapping("/complaints")
    fun complaints(model: ModelMap, auth: Authentication?){
        if(auth != null) model["user"] = auth
        model["domain"] = domainRepository.findAll()
        model["complaints"] = complaintRepository.findAll()
        

    }

    @RequestMapping("/complaints/{domain}")
    fun compDomain(){

    }

    @RequestMapping("/complaints/{domain}/sub")
    fun compSub(){

    }

    @PostMapping("/complaints/{domain}/new")
    fun newComplaintAction(){

    }

    /*
    @GetMapping("/")
    fun showHomePage(principal: Principal): ModelAndView? {
        val name = principal.name
        val modelAndView = ModelAndView("home")
        modelAndView.addObject("username", name)
        return modelAndView
    }
*/

}