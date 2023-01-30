package etu.spring.td1.models

data class Categorie (var label:String) {
    val items: Set<Item> = HashSet<Item>()
    operator fun get(itemName:String):Item?{
        return items.find{it.nom==itemName} //it : itérateur
    }
    //operator : surdéfinition du get -> permet d'accéder à ["qqch"] de la catégorie["qqch"]

}