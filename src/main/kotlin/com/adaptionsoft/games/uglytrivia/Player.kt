package com.adaptionsoft.games.uglytrivia

class Player(val name: String) {
    private var inPenaltyBox: Boolean = false
    private var purse: Int = 0
    var place: Int = 0
    var canAnswer: Boolean = true

    fun hasWon(): Boolean = purse == 6

    fun isInPenaltyBox(): Boolean {
        return inPenaltyBox
    }

    fun goesToPenaltyBox() {
        inPenaltyBox = true
    }

    fun moveForward(roll: Int) {
        place = (place + roll) % 12
    }

    fun scoresAPoint() {
        purse++
    }

    fun numberOfPoints(): Int = purse
}
