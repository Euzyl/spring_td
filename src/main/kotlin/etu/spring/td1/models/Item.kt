package etu.spring.td1.models

data class Item (var nom:String){
    var evaluation:Int=0
        set(value) {
            if(value in 0..10) field=value
        }

        /*
        set(value){
            if(value in 0<=..<=10)field=value
        }

         */
/*
    override fun equals(other: Any?): Boolean {
        if(other===this){
            return true
        }
        if(other !is Item){
            return false
        }
        return other.nom==this.nom
    }

    override fun hashCode(): Int {
        return nom.hashCode()
    }
 */
}