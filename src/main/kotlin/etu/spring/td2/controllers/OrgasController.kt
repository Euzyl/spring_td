package etu.spring.td2.controllers

import etu.spring.td2.models.Organization
import etu.spring.td2.repositories.OrgaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/orgas/")
class OrgasController {

    @Autowired
    lateinit var repo: OrgaRepository

    @PostMapping("new")
    @ResponseBody
    fun newOrga(@ModelAttribute orga: Organization?): String {
        repo.saveAndFlush(orga)
        return "${orga} ajoutée."
    }

    @PostMapping("edit/{id}")
    fun editOrga(@PathVariable id: Int){

    }

    @GetMapping("display/{id}")
    fun displOrga(@PathVariable id: Int){

    }

    @RequestMapping("delete/{id}")
    fun delOrga(@PathVariable id: Int){

    }

}