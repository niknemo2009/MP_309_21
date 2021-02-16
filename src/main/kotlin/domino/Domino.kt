package domino

data class Domino(val leftValue: Int, val rightValue: Int) {
    fun reversed(): Domino {
        return Domino(rightValue, leftValue)
    }
    override fun toString(): String {
        return "|$leftValue||$rightValue|"
    }
}