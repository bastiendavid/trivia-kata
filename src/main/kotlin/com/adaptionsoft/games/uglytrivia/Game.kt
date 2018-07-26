package com.adaptionsoft.games.uglytrivia

class Game {

    var players: Players = Players(this)

    constructor(player1: String, player2: String) {
        players.join(player1, player2)
    }

    constructor(player1: String, player2: String, player3: String) {
        players.join(player1, player2, player3)
    }

    constructor(player1: String, player2: String, player3: String, player4: String) {
        players.join(player1, player2, player3, player4)
    }

    constructor(player1: String, player2: String, player3: String, player4: String, player5: String) {
        players.join(player1, player2, player3, player4, player5)
    }

    constructor(player1: String, player2: String, player3: String, player4: String, player5: String, player6: String) {
        players.join(player1, player2, player3, player4, player5, player6)
    }

    private val categories = arrayOf(Pop(), Science(), Sports(), Rock())

    fun roll(roll: Int) {
        players.current().rolls(roll)
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

    fun boardSize(): Int = categories.size * 3

}