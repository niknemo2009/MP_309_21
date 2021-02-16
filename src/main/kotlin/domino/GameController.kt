package domino

import Player

class GameController(private val players: MutableList<DominoPlayer>) {

    private val storage: MutableList<Domino> = mutableListOf()
    private var table: MutableList<Domino> = mutableListOf()
    private val totals: MutableList<DominoPlayer> = mutableListOf()
    private var range: IntRange = 0..0

    init {
        initStorage()

        if(handOutDomino()){
            doFirstStep()
            gameProcess()
        }
        else
            println("Players can be from 2 to 4")
    }

    private fun doFirstStep() {
        // TODO: Логику выбора первого ходящего
        table.add(players[0].storage.removeAt(0))
    }

    private fun initStorage() {
        fun isDominoExists(i: Int, j: Int): Boolean {
            for(d in storage)
                if(d.component1() == i && d.component2() == j)
                    return true

            return false
        }

        for(i in 1..6)
            for(j in 1..6)
                if(!isDominoExists(i, j))
                    storage.add(Domino(i, j))

        storage.shuffle()
    }

    private fun handOutDomino(): Boolean {
        range = when(players.size) {
            2 -> 1..7
            3 -> 1..6
            4 -> 1..5
            else -> return false
        }

        for(i in players)
            for(j in range)
                i.storage.add(storage.removeAt(0))

        return true
    }

    private fun gameProcess() {
        if(players.any { it.storage.size != 0 }) {
            val passes = mutableListOf<DominoPlayer>()
            for (i in players.indices) {
                val domino = players[i].makeNextStep(mutableListOf(table[0], table[table.size - 1]))

                if(domino == null){
                    if(storage.size != 0) {
                        players[i].storage.add(storage.removeAt(0))
                        println("--${players[i].name} skipped")
                    }
                    passes.add(players[i])
                }
                else {
                    if(domino.rightValue == table[0].leftValue && domino.leftValue != table[table.size - 1].rightValue)
                        table.add(0, domino)
                    if(domino.leftValue == table[table.size - 1].rightValue)
                        table.add(domino)

                    println("--${players[i].name} throws $domino on the table")
                }
            }

            for(i in players){
                for(j in i.storage.size..range.last)
                    if(storage.size != 0)
                        i.storage.add(storage.removeAt(0))
            }

            for(i in players) {
                if(storage.size == 0 && i.storage.size == 0)
                    totals.add(i)
            }

            println("\nTABLE: $table\n")

            if(passes.size == players.size - players.filter { it.storage.size != 0 }.size)
                gameProcess()
            else{
                println("-----FISH-----")

                for(i in totals.indices)
                    println("-----${i+1} PLACE-----\n ${totals[i]}")
            }
        }
        else for(i in totals.indices)
                println("-----${i+1} PLACE-----\n ${totals[i]}")

    }
}

fun main(){
    GameController(mutableListOf(
        Player("Egor"),
        Player("Stephanie"),
        Player("Artem"),
        Player("Sasha"),
    ))
}