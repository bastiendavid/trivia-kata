package com.adaptionsoft.games.uglytrivia

class Game {

    var players: Players = Players(this)
    var categories: Categories = Categories()

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

    fun roll(roll: Int) {
        currentPlayer().rolls(roll)
    }

    fun askQuestion() {
        log("The category is " + currentCategory().name())
        log(currentCategory().question())
    }

    fun wasCorrectlyAnswered() {
        currentPlayer().gaveAGoodAnswer()
        players.next()
    }

    fun wrongAnswer() {
        currentPlayer().gaveAWrongAnswer()
        players.next()
    }

    private fun currentCategory(): Category = categories.at(currentPlayerPlace())

    private fun currentPlayer() = players.current()

    private fun currentPlayerPlace() = currentPlayer().place

    fun boardSize(): Int = categories.count() * 3

    fun isOver(): Boolean = players.hasAWinner()

    private fun log(message: String) {
        Logger.get().log(message)
    }
}