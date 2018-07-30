package com.adaptionsoft.games.uglytrivia

class Player(val name: String, private val game: Game) {
    private var inPenaltyBox: Boolean = false
    private var purse: Int = 0
    var place: Int = 0
    var canAttemptToLeavePenaltyBox: Boolean = false

    fun hasWon(): Boolean = purse == 6

    fun rolls(roll: Int) {
        log("$name is the current player")
        log("They have rolled a $roll")

        when (inPenaltyBox) {
            true -> attemptsToLeavePenaltyBox(roll)
            else -> plays(roll)
        }
    }


    private fun plays(roll: Int) {
        moveOnBoard(roll)
        game.askQuestion()
    }

    private fun moveOnBoard(roll: Int) {
        place = (place + roll) % game.boardSize()
        log("$name's new location is $place")
    }

    private fun attemptsToLeavePenaltyBox(roll: Int) {
        canAttemptToLeavePenaltyBox = rollIsOdd(roll)
        when (canAttemptToLeavePenaltyBox) {
            true -> plays(roll)
            else -> staysInPenaltyBox()
        }
    }

    private fun rollIsOdd(roll: Int): Boolean = roll % 2 != 0

    fun gaveAGoodAnswer() {
        if (inPenaltyBox && !canAttemptToLeavePenaltyBox) {
            return
        }
        log("Answer was correct!!!!")
        scoresAPoint()
        leavesPenaltyBox()
    }

    private fun scoresAPoint() {
        purse++
        log("$name now has $purse Gold Coins.")
    }

    fun gaveAWrongAnswer() {
        log("Question was incorrectly answered")
        goesToPenaltyBox()
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

    private fun log(message: String) {
        Logger.get().log(message)
    }
}
