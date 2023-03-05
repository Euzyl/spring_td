package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import edu.spring.dogs.repositories.DogRepository
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

    @Autowired
    lateinit var dogRepository:DogRepository

    @RequestMapping(path = ["/","","index"])
    fun indexAction(model:ModelMap):String {
        model.addAttribute("masters", masterRepository.findAll())
        model.addAttribute("dogs", dogRepository.findAll())
        model.addAttribute("master", Master(firstname = "", lastname = ""))

        val masters = masterRepository.findAll()
        if (masters.count() == 0) {
            model.addAttribute("isMasterEmpty", true)
        }

        val dogs = dogRepository.findAll()
        if(dogs.count()==0){
            model.addAttribute("isDogEmpty", true)
        }
        return "index"
    }

    @PostMapping("/master/add")
    fun addMasterAction(@ModelAttribute master:Master): RedirectView {
        masterRepository.save(master)
        return RedirectView("/")
    }


    @PostMapping("/master/{id}/dog")
    fun masterAction(
        @PathVariable id: Int,
        @ModelAttribute dog: Dog,
        @RequestParam("dog-action") action: String
    ): RedirectView {
        val master = masterRepository.findById(id).orElse(null)
        if (action == "add") {
            if (master != null) {
                dog.master = master
                dogRepository.save(dog)
            }
        }else if (action == "giveup") {
            dog.master = null
            dogRepository.save(dog)
        }
        return RedirectView("/")
    }

    @PostMapping("/dog/{id}/action")
    fun dogAction(
        @PathVariable id:Int,
        @RequestParam("selection") sel : Int,
        @RequestParam("dog-action") action:String
    ):RedirectView{
        if(action == "remove"){
            val del = dogRepository.findById(id)
            if(del.isPresent){
                dogRepository.delete(del.get())
            }
        }else if(action == "adopt"){
            val dog = dogRepository.findById(id)
            val master = masterRepository.findById(sel).orElse(null)
            dog.get().master = master
        }
        return RedirectView("/")
    }





    @PostMapping("/master/{id}/delete")
    fun deleteMaster(@PathVariable id:Int):RedirectView{
        val del=masterRepository.findById(id)
        if(del.isPresent) {
            masterRepository.delete(del.get())
        }
        return RedirectView("/")
    }

}