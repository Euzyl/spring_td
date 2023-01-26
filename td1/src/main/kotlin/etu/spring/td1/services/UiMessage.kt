package etu.spring.td1.services

class UiMessage {
    class Message(var title:String, var message: String,var type:String,var icon:String)
    //val = constante, var = variable, on est content : tout en un (getter, setter, constructeur, attributs)
    //companion object = methodes statiques / de classe
    companion object{
        fun message(title:String, message:String, type:String, icon: String) = //le égal est un return
                Message(title, message, type, icon)
        fun message(title: String,message: String)=Message(title, message, "info", "info circle")
    }
}