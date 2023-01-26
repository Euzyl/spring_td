package etu.spring.td1.controller

import etu.spring.td1.models.Item
import etu.spring.td1.services.UiMessage
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@SessionAttributes("items")
class itemController {
    @get:ModelAttribute("items")
    val items:Set<Item>
        get() {
            var items=HashSet<Item>()
            items.add(Item("Foo"))
            return items
        }

    @RequestMapping("/")
    fun indexAction(@RequestAttribute("msg") msg:UiMessage.Message?): String {
        //? = peut etre null, on récupère l'éventuel msg
        return "index"
    }

    @GetMapping("/new")
    fun newAction():String{
        return "newForm"
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
}