package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import edu.spring.dogs.repositories.MasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/", "")
class MainController {
    @Autowired
    lateinit var masterRepository: MasterRepository

    @RequestMapping(path = ["/","","index"])
    fun indexAction(model:ModelMap):String {
        model["masters"] = masterRepository.findAll()
        return "index"
    }

    @PostMapping("/master/add")
    fun addMasterAction(
        @ModelAttribute master:Master
    ):RedirectView{
        return RedirectView("/")
    }

    @PostMapping("/master/{id}/dog")
    fun dogAction(
        @ModelAttribute dog: Dog
    ):RedirectView{

        return RedirectView("/")
    }

    @GetMapping("/master/{id}/delete")
    fun deleteMaster(@PathVariable id:Int):RedirectView{
        val del=masterRepository.findById(id)
        if(del.isPresent) {
            masterRepository.delete(del.get())
        }
        return RedirectView("/")
    }









}