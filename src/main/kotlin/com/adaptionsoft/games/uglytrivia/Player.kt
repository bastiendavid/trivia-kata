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

    fun moveOnBoard(roll: Int) {
        place = (place + roll) % 12
        Logger.get().log("$name's new location is $place")

    }

    fun scoresAPoint() {
        purse++
        Logger.get().log("$name now has $purse Gold Coins.")

    }

    fun gaveAGoodAnswer() {
        if (canAnswer) {
            Logger.get().log("Answer was correct!!!!")
            scoresAPoint()
        }
    }

    fun gaveAWrongAnswer() {
        Logger.get().log("Question was incorrectly answered")
        Logger.get().log("$name was sent to the penalty box")
        goesToPenaltyBox()
    }
}
