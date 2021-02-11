interface DominoPlayer {
    val name: String
    val storage: MutableList<Domino>
    fun makeNextStep(table: MutableList<Domino>): Domino?
}