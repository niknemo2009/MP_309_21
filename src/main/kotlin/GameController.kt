class GameController(val plaers:List<Player>) {
    val market:MutableList<Domino> = mutableListOf();
    val table:MutableList<Domino> = mutableListOf();
        var currenPlayer:Int=0;
    init{
        generateMarket()
        makePersonalSet()
        firsStep()
    }

    private fun firsStep() {
        //TODO("Not yet implemented")
        plaers.get(currenPlayer).nextStep(table)
        currenPlayer=if (currenPlayer==3) 0  else currenPlayer++
    }

    private fun makePersonalSet() {
       // TODO("Not yet implemented")
        for (gamer in plaers){
         for(i in 1..7){
             gamer.personalSet.add(market.get(0))
             market.removeAt(0)
         }
        }
    }

    private fun generateMarket() {
       // TODO("Not yet implemented")
        for(i:Int in 0..6){
           for (j:Int in 0..6){
              var tempDomino:Domino=Domino(i,j)
               if(!findDomino(i,j)){
                   market.add(tempDomino)
               }
           }
        }
        market.shuffle()
    }

    private fun findDomino(i: Int, j: Int):Boolean {
       var  result=false
        for ( temp in market){
        if((temp.leftValue===i&&temp.rightValue===j)||(temp.leftValue===j&&temp.rightValue===i))
        return true
        }
       return result
    }

  fun  viewTable(){
      println(market)
      println(plaers)
      println(table)
  }
}

fun main() {
    var pl1=Player("Ivan")
    var pl2=Player("Egor")
    var pl3=Player("Oleksandr")
    var pl4=Player("Serg")
    var list:MutableList<Player> = mutableListOf(pl1,pl2,pl3,pl4)
    var game:GameController = GameController(list)
    game.viewTable()

}