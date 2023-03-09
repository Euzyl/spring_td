package edu.spring.dogs.controllers

import edu.spring.dogs.repositories.DogRepository
import edu.spring.dogs.repositories.MasterRepository
import io.github.jeemv.springboot.vuejs.VueJS
import io.github.jeemv.springboot.vuejs.utilities.Http
import io.github.jeemv.springboot.vuejs.utilities.JsArray
import net.minidev.json.JSONArray
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class SPAController {
    @Autowired
    lateinit var masterRepository: MasterRepository

    @Autowired
    lateinit var dogRepository:DogRepository

    @Autowired
    lateinit var vue: VueJS

    @ModelAttribute("vue")
    fun getVueInstance() = vue

    @RequestMapping(path=["/",""])
    fun index() : String{
        vue.addData("masters",masterRepository.findAll())
        //vue.addDataRaw("newMaster", "{}")
        vue.addDataRaw("newMaster", "[]")
        vue.addData("dogs", dogRepository.findByMasterIsNull())

        //génération de javascript en kotlin dans une chaine de caracteres
        vue.addMethod("removeMaster",
            Http.delete("'/masters/'+master.id",
                JsArray.remove("this.masters", "master"),
                "console.log('Erreur')"),
            "master")

        vue.addMethod(
            "",
            Http.post("/masters",
                "masters",
                JsArray.add("this.masters","master"),
                "console.log('Erreur')"),
            "master"
        )

        vue.onMounted(Http.get("/masters",
            Http.responseArrayToArray("this.masters","masters" ),
            "console.log('Erreur')")
        )

        return "/spa/index"
    }


}