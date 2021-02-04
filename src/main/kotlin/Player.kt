class Player(val name:String){
       val personalSet:MutableList<Domino> =mutableListOf()


    fun  nextStep(table: MutableList<Domino>):Domino? {

        return null
    }

    override fun toString(): String {
        return "Player(name='$name', personalSet=$personalSet) \n"
    }

}