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
        model.addAttribute("dogsAdopt", dogRepository.findByMasterIsNull())

        val masters = masterRepository.findAll()
        if (masters.count() == 0) {
            model.addAttribute("isMasterEmpty", true)
        }

        val dogs = dogRepository.findAll()
        val dogsSPA = dogRepository.findByMasterIsNull()
        if(dogsSPA.count()==0){
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
        }else if (action == "give-up") {
            //val doggo = dogRepository.findByNameAndMasterId(dog.name, id) //renvoie null

            master.giveUpDog(dog)
            //dog.master = null
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
        val dog = dogRepository.findById(id).orElse(null)
        val master = masterRepository.findById(sel).orElse(null)
        if (master != null) {
            masterRepository.save(master)
        }
        if(action == "remove"){
            dogRepository.delete(dog)
        }else if(action == "adopt"){
            dog.master = master
            master.addDog(dog)
            dogRepository.save(dog)
        }
        return RedirectView("/")
    }

    @PostMapping("/master/{id}/delete")
    fun deleteMaster(@PathVariable id:Int):RedirectView{
        val master=masterRepository.findById(id).orElse(null)
        master.preRemove()
        masterRepository.delete(master)
        return RedirectView("/")
    }

}