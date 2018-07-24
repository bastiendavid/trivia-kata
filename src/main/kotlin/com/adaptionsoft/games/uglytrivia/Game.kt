package com.adaptionsoft.games.uglytrivia

class Game(private val players: Players) {

    private val categories = arrayOf(Pop(), Science(), Sports(), Rock())

    fun roll(roll: Int) {
        players.current().rolls(this, roll)
    }

    fun askQuestion() {
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