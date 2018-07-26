package com.adaptionsoft.games.uglytrivia

class Player(val name: String) {
    private var inPenaltyBox: Boolean = false
    private var purse: Int = 0
    var place: Int = 0
    var canAttemptToLeavePenaltyBox: Boolean = false

    fun hasWon(): Boolean = purse == 6

    fun moveOnBoard(roll: Int) {
        place = (place + roll) % 12
        log("$name's new location is $place")

    }

    fun scoresAPoint() {
        purse++
        log("$name now has $purse Gold Coins.")

    }

    fun gaveAGoodAnswer() {
        if (inPenaltyBox && !canAttemptToLeavePenaltyBox) {
            return
        }
        log("Answer was correct!!!!")
        scoresAPoint()
        leavesPenaltyBox()
    }

    fun gaveAWrongAnswer() {
        log("Question was incorrectly answered")
        goesToPenaltyBox()
    }


    fun rolls(game: Game, roll: Int) {
        log("$name is the current player")
        log("They have rolled a $roll")

        if (inPenaltyBox) {
            canAttemptToLeavePenaltyBox = rollIsOdd(roll)
            if (!canAttemptToLeavePenaltyBox) {
                staysInPenaltyBox()
                return
            }
        }
        moveOnBoard(roll)
        game.askQuestion()
    }

    private fun staysInPenaltyBox() {
        log("$name is not getting out of the penalty box")
    }

    private fun leavesPenaltyBox() {
        log("$name is getting out of the penalty box")
        inPenaltyBox = false
    }

    fun goesToPenaltyBox() {
        log("$name was sent to the penalty box")
        inPenaltyBox = true
    }

    private fun rollIsOdd(roll: Int): Boolean = roll % 2 != 0

    private fun log(message: String) {
        Logger.get().log(message)
    }
}
