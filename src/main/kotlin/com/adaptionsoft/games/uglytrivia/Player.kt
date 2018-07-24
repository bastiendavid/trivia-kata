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
        log("$name's new location is $place")

    }

    fun scoresAPoint() {
        purse++
        log("$name now has $purse Gold Coins.")

    }

    fun gaveAGoodAnswer() {
        if (canAnswer) {
            log("Answer was correct!!!!")
            scoresAPoint()
        }
    }

    fun gaveAWrongAnswer() {
        log("Question was incorrectly answered")
        log("$name was sent to the penalty box")
        goesToPenaltyBox()
    }

    fun rolls(roll: Int) {
        log("$name is the current player")
        log("They have rolled a $roll")
    }

    private fun log(message: String) {
        Logger.get().log(message)
    }
}
