package com.adaptionsoft.games.uglytrivia

class Player(val name: String) {
    private var inPenaltyBox: Boolean = false
    private var purse: Int = 0
    var place: Int = 0
    var canAnswer: Boolean = true

    fun hasWon(): Boolean = purse == 6

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

    fun rolls(game: Game, roll: Int) {
        log("$name is the current player")
        log("They have rolled a $roll")

        if (inPenaltyBox) {
            val rollAllowsToLeavePenaltyBox = rollIsOdd(roll)
            when (rollAllowsToLeavePenaltyBox) {
                true -> leavesPenaltyBox()
                false -> staysInPenaltyBox()
            }
            if (!rollAllowsToLeavePenaltyBox) return
        }
        moveOnBoard(roll)
        game.askQuestion()
    }

    private fun staysInPenaltyBox() {
        log("$name is not getting out of the penalty box")
        canAnswer = false
    }

    private fun leavesPenaltyBox() {
        canAnswer = true
        log("$name is getting out of the penalty box")
    }

    private fun rollIsOdd(roll: Int): Boolean = roll % 2 != 0

    private fun log(message: String) {
        Logger.get().log(message)
    }
}
