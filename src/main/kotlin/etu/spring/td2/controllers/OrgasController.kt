package etu.spring.td2.controllers

import etu.spring.td2.models.Organization
import etu.spring.td2.models.User
import etu.spring.td2.repositories.OrgaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView
import java.lang.ProcessBuilder.Redirect

@Controller
@RequestMapping("/orgas")
class OrgasController {

    @Autowired
    lateinit var orgaRepository: OrgaRepository

    @RequestMapping(path = ["","/index","/"])
    fun indexAction(model : ModelMap):String{
        model["orgas"]=orgaRepository.findAll() //=select * from organisation
        return "/orgas/index"
    }

    @GetMapping("/new")
    fun newAction():ModelAndView{
        val mv=ModelAndView("/orgas/form")
        mv.addObject("orga",Organization(""))
        return mv
    }



    @PostMapping("/new")
   // @ResponseBody
    fun newSubmitAction(
        @ModelAttribute orga: Organization,
        @ModelAttribute("users") users:String

    ): RedirectView {
        if(users.isNotEmpty()){
            users.split("\n").forEach(){
                val user = User()
                val (firstname, lastname)=it.trim().split(" ", limit = 2)
                user.firstname=firstname
                user.lastname=lastname
                user.email="$firstname.$lastname@${orga.domain}".lowercase()
                orga.addUser(user)
            }
        }

        orgaRepository.save(orga)
        //return "${orga} ajoutée."
        return RedirectView("/orgas")
    }

    @PostMapping("/edit/{id}")
    fun editOrga(@PathVariable id: Int){

    }

    @GetMapping("/display/{id}")
    fun displOrga(@PathVariable id: Int){

    }

    @RequestMapping("/delete/{id}")
    fun delOrga(@PathVariable id: Int){

    }

}