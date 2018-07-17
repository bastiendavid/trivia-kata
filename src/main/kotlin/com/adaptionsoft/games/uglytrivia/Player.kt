package com.adaptionsoft.games.uglytrivia

class Player(val name: String) {
    private var inPenaltyBox: Boolean = false
    var purse: Int = 0

    fun hasWon(): Boolean = purse == 6

    fun isInPenaltyBox(): Boolean {
        return inPenaltyBox
    }

    fun goesToPenaltyBox() {
        inPenaltyBox = true
    }

}
