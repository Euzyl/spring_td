package etu.spring.td1.controller

import etu.spring.td1.models.Category
import etu.spring.td1.models.Item
import etu.spring.td1.services.UiMessage
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView


@Controller
@SessionAttributes("categories")
class ItemController {

    //lire le kotlin guide -> the !! operator

    /* pas utilisé
    private fun getItemByName(nom: String, items: HashSet<Item>):
            Item? = items.find{nom==it.nom}
    */
    private fun getCategoryByLabel(label:String,categories:HashSet<Category>):
            Category?=categories.find { label==it.label }

    /* pas utilisé
    private fun addMsg(resp:Boolean,attrs: RedirectAttributes,title:String,success:String,error:String){
        if(resp) {
            attrs.addFlashAttribute("msg",
                UiMessage.message(title, success))
        } else {
            attrs.addFlashAttribute("msg",
                UiMessage.message(title, error,"error","warning circle"))
        }
    }*/

    private fun addMsg(category:String,resp:Boolean,attrs: RedirectAttributes,title:String,success:String,error:String){
        attrs.addFlashAttribute("category",category)
        if(resp) {
            attrs.addFlashAttribute("msg",
                UiMessage.message(title, success))
        } else {
            attrs.addFlashAttribute("msg",
                UiMessage.message(title, error,"error","warning circle"))
        }
    }

    @get:ModelAttribute("categories")
    val categories: Set<Category>
        get() {
            val cats= HashSet<Category>()
            cats.add(Category.all)
            cats.add(Category.create("Animaux","Chat","Chien","Lama","Alpaga","Hippopotame","Koala"))
            cats.add(Category.create("Plantes","Orchidee","Tulipe","Rose","Violette","Lavande","Lys"))
            return cats
        }

    @RequestMapping("/")
    fun indexAction(@RequestAttribute("msg") msg:UiMessage.Message?): String {
        //? = peut etre null, on récupère l'éventuel msg
        return "index"
    }

    @GetMapping("/new/{category}")
    fun newAction(model:ModelMap,@PathVariable category: String):String{
        //PathVariable => {}
        model["item"] = Item("")
        model["url"]="/addNew/$category"
        return "itemForm"
    }

    @PostMapping("/addNew/{category}")
    fun addNewAction(
            @ModelAttribute("nom") nom:String,
            @SessionAttribute("categories") categories:HashSet<Category>,
            @PathVariable category: String,
            attrs: RedirectAttributes): RedirectView {
        addMsg(
            category,
            getCategoryByLabel(category,categories)?.add(nom)?:false,
                                        // ?? que signifie le :false ?
            attrs,
            "Ajout",
            "$nom a été ajouté avec succès dans $category",
            "$nom est déjà dans la catégory $category, il n'a pas été ajouté"
        )
        /*
        if(items.add(Item(nom))) {
            attrs.addFlashAttribute("msg",
                    UiMessage.message("Ajout d'item", "$nom a été ajouté avec succès"))
        }else{
            attrs.addFlashAttribute("msg",
                    UiMessage.message("Ajout d'item", "$nom est déjà dans la liste,<br>Il n'a pas été ajouté",
                            "warning","warning circle"))
        }
         */

        return RedirectView("/")
    }

/* pas utilisé
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

 */
    @GetMapping("/update/{category}/{nom}")
    fun updateAction(
        @PathVariable nom: String,
        @PathVariable category: String,
        @SessionAttribute("categories") categories:HashSet<Category>,
        ):ModelAndView{
        val mv=ModelAndView("itemForm")
        val item = getCategoryByLabel(category, categories)?.get(nom)
        mv.addObject("item",item)
        mv.addObject("url","/update/$category")
        return mv
    }

    @PostMapping("/update/{category}")
    fun updateSubmitAction(
        @ModelAttribute("nom") nom: String,
        @ModelAttribute("id") id:String,
        @PathVariable category: String,
        @SessionAttribute("categories") categories: HashSet<Category>,
        attrs:RedirectAttributes):RedirectView{
        val item=getCategoryByLabel(category,categories)?.get(id)
        if(item!=null){
            item.nom = nom
        }
        addMsg(
            category,
            item!=null,
            attrs,
            "Modiffications",
            "$nom modifié",
            "$nom n'existe pas"
        )
        return RedirectView("/")
    }

    @GetMapping("/inc/{category}/{nom}")
    fun incAction(
        @PathVariable nom:String,
        @PathVariable category: String,
        @SessionAttribute("categories") categories:HashSet<Category>,
        attrs: RedirectAttributes
    ):RedirectView{
        val item=getCategoryByLabel(category,categories)?.get(nom)
        item?.evaluation=item!!.evaluation+1
        addMsg(
            category,
            item!=null,
            attrs,
            "Mise à jour",
            "$nom incrémenté",
            "$nom n'existe pas"
        )
        return RedirectView("/")
    }


    @GetMapping("/dec/{category}/{nom}")
    fun decAction(
        @PathVariable nom:String,
        @PathVariable category: String,
        @SessionAttribute("categories") categories:HashSet<Category>,
        attrs: RedirectAttributes
    ):RedirectView{
        val item=getCategoryByLabel(category,categories)?.get(nom)
        item?.evaluation=item!!.evaluation-1
        addMsg(
            category,
            item!=null,
            attrs,
            "Mise à jour",
            "$nom décrémenté",
            "$nom n'existe pas dans les items"
        )
        return RedirectView("/")
    }

}