package com.adaptionsoft.games.uglytrivia

class Game(private val players: Players) {

    private val categories = arrayOf(Pop(), Science(), Sports(), Rock())

    fun roll(roll: Int) {
        players.current().rolls(roll)

        if (players.current().isInPenaltyBox()) {
            when (rollIsOdd(roll)) {
                true -> {
                    players.current().canAnswer = true
                    log(players.current().name + " is getting out of the penalty box")
                    currentPlayerMoveOnBoard(roll)
                    askQuestion()
                }
                false -> {
                    log(players.current().name + " is not getting out of the penalty box")
                    players.current().canAnswer = false
                }
            }
            return
        } else {

            currentPlayerMoveOnBoard(roll)
            askQuestion()
        }
    }

    private fun currentPlayerMoveOnBoard(roll: Int) {
        players.current().moveOnBoard(roll)
    }

    private fun rollIsOdd(roll: Int): Boolean = roll % 2 != 0

    private fun askQuestion() {
        log("The category is " + currentCategory().name())
        log(currentCategory().question())
    }

    private fun currentCategory(): Category = categories[currentPlayerPlace() % categories.size]

    private fun currentPlayerPlace() = players.current().place

    fun wasCorrectlyAnswered(): Boolean {
        players.current().gaveAGoodAnswer()
        val gameContinues = gameContinues()
        players.next()
        return gameContinues
    }

    fun wrongAnswer(): Boolean {
        players.current().gaveAWrongAnswer()
        players.next()
        return true
    }

    private fun log(message: String) {
        Logger.get().log(message)
    }

    private fun gameContinues(): Boolean {
        return !players.current().hasWon()
    }

}