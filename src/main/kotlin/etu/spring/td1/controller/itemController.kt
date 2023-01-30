package etu.spring.td1.controller

import etu.spring.td1.models.Categorie
import etu.spring.td1.models.Item
import etu.spring.td1.services.UiMessage
import jdk.jfr.Category
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@SessionAttributes("items")
class itemController {

    //lire le kotlin guide -> the !! operator

    private fun getItemByName(nom: String, items: HashSet<Item>):
            Item? = items.find{nom==it.nom}

    private fun addMsg(resp:Boolean,attrs: RedirectAttributes,title:String,success:String,error:String){
        if(resp) {
            attrs.addFlashAttribute("msg",
                UiMessage.message(title, success))
        } else {
            attrs.addFlashAttribute("msg",
                UiMessage.message(title, error,"error","warning circle"))

        }
    }

    @get:ModelAttribute("categories")
    val items:Set<Categorie>
        get() {
            var cats=HashSet<Categorie>()
            val cat = Categorie("Foo")
            cats.add(cat)

            return cats
        }

    @RequestMapping("/")
    fun indexAction(@RequestAttribute("msg") msg:UiMessage.Message?): String {
        //? = peut etre null, on récupère l'éventuel msg
        return "index"
    }

    @GetMapping("/new")
    fun newAction(model:ModelMap):String{
        model["item"] = Item("")
        return "itemForm"
    }

    @PostMapping("/addNew")
    fun addNewAction(
            @ModelAttribute("nom") nom:String,
            @SessionAttribute("items") items:HashSet<Item>,
            attrs: RedirectAttributes): RedirectView {
        if(items.add(Item(nom))) {
            attrs.addFlashAttribute("msg",
                    UiMessage.message("Ajout d'item", "$nom a été ajouté avec succès"))
        }else{
            attrs.addFlashAttribute("msg",
                    UiMessage.message("Ajout d'item", "$nom est déjà dans la liste,<br>Il n'a pas été ajouté",
                            "warning","warning circle"))
        }
        return RedirectView("/")
    }

    @RequestMapping("/delete")
    fun deleteAction(
            @ModelAttribute("nom") nom:String,
            @SessionAttribute("items") items:HashSet<Item>,
            attrs: RedirectAttributes): RedirectView {
        if(items.remove(Item(nom))){
            attrs.addFlashAttribute("msg",
                UiMessage.message("Suppression d'item", "$nom a été supprimé avec succès"))
        }else{
            attrs.addFlashAttribute("msg",
                UiMessage.message("Suppression d'item", "$nom n'existe pas",
                    "warning","warning circle"))
        }
        return RedirectView("/")
    }

    @GetMapping("/update/{nom}")
    fun updateAction(
        @PathVariable nom: String,
        @SessionAttribute("items") items:HashSet<Item>,
        ):ModelAndView{
        val mv=ModelAndView("itemForm")
        val item = getItemByName(nom, items)
        mv.addObject("item",item)
        mv.addObject("url","/update")
        return mv
    }

    @PostMapping("/update")
    fun updateSubmitAction(
        @ModelAttribute("nom") nom: String,
        @SessionAttribute("items") items:HashSet<Item>,
        attrs:RedirectAttributes):RedirectView{
        val item=getItemByName(nom, items)
        if(item!=null){
            item.nom = nom
        }
        addMsg(
            item!=null,
            attrs,
            "Modiffications",
            "$nom modifié",
            "$nom n'existe pas"
        )
        return RedirectView("/")
    }




    @RequestMapping("/inc/{nom}")
    fun incAction(
        @PathVariable nom:String,
        @SessionAttribute("items") items:HashSet<Item>,
        attrs: RedirectAttributes
    ):RedirectView{
        val item=getItemByName(nom, items)
        item?.evaluation=item!!.evaluation+1
        addMsg(
            item!=null,
            attrs,
            "Mise à jour",
            "$nom incrémenté",
            "$nom n'existe pas"
        )
        return RedirectView("/")
    }


    @GetMapping("/dec/{nom}")
    fun decAction(
        @PathVariable nom:String,
        @SessionAttribute("items") items:HashSet<Item>,
        attrs:RedirectAttributes
    ):RedirectView{
        val item=getItemByName(nom,items)
        item?.evaluation =item!!.evaluation-1
        addMsg(
            item!=null,
            attrs,
            "Mise à jour",
            "$nom décrémenté",
            "$nom n'existe pas dans les items"
        )
        return RedirectView("/")
    }



}