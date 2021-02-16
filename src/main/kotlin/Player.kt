import domino.Domino
import domino.DominoPlayer

class Player(override val name:String, override val storage: MutableList<Domino> = mutableListOf()) : DominoPlayer {
    override fun makeNextStep(table: MutableList<Domino>): Domino? {
        fun canMakeNextStep(i: Domino, j: Domino): Boolean {
            return i.component1() == j.component1()
                    || i.component2() == j.component1()
                    || i.component1() == j.component2()
                    || i.component2() == j.component2()
        }

        for(i in table)
            for(j in storage.indices)
                if(canMakeNextStep(i, storage[j]))
                    return if(i.rightValue != storage[j].leftValue || i.leftValue != storage[j].rightValue) {
                        storage.removeAt(j).reversed()
                    } else
                        storage.removeAt(j)

        return null
    }

    override fun toString(): String {
        return "$name: $storage"
    }
}