package etu.spring.td2.controllers

import etu.spring.td2.exceptions.ElementNotFoundException
import etu.spring.td2.models.Organization
import etu.spring.td2.models.User
import etu.spring.td2.repositories.OrgaRepository
import etu.spring.td2.services.OrgaService
import org.aspectj.apache.bcel.classfile.ExceptionTable
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

    @Autowired
    lateinit var orgaService: OrgaService

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
        orgaService.addUsersToOrga(orga,users)
        orgaRepository.save(orga)
        //return "${orga} ajoutée."
        return RedirectView("/orgas")
    }

    @PostMapping("/edit/{id}")
    fun editOrga(@PathVariable id: Int){

    }

    @GetMapping("/display/{id}")
    fun displayAction(@PathVariable id: Int, model : ModelMap):String{
        val option=orgaRepository.findById(id)
        if(option.isPresent){
            model["orga"]=option.get()
            return "/orgas/display"
        }
        throw ElementNotFoundException("Organisation d'identifiant $id inexistante !!")
    }
/*
    @RequestMapping("/delete/{id}")
    fun deleteAction(@PathVariable id: Int):RedirectView{
        orgaRepository.deleteById(id)
        return RedirectView("/orgas/")
    }

    @ExceptionHandler
    @ResponseBody
    fun errorAction(ex:Exception):String?{
        return ex.message
    }

    @ExceptionHandler//(value = [ElementNotFoundException::class])
    fun exceptionHandler(ex:RuntimeException):ModelAndView {
        val mv=ModelAndView("/main/error")
        mv.addObject("message",ex.message)
        return mv
    }
    */
    @GetMapping("/delete/{id}")
    fun deleteAction(@PathVariable id : Int):RedirectView{
        val option = orgaRepository.findById(id)
        if(option.isPresent) {
            orgaRepository.delete(option.get())
            return RedirectView("/orgas")
        }
        throw ElementNotFoundException("Organisation $id intoruvable")
    }

    @GetMapping("/{id}/groups/new")
    fun addNewGroup(@PathVariable id:Int):String{

    }

    @PostMapping("/{id}/groups/new")
    fun addNewSubmitAction(
        @ModelAttribute("idOrga") id:Int
        @ModelAttribute("group")
    )


}