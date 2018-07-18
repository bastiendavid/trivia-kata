package com.adaptionsoft.games.uglytrivia

import java.util.*

class Game(private val logger: Logger = Logger()) {

    var players = ArrayList<Player>()
    var currentPlayerIndex = 0
    var isGettingOutOfPenaltyBox: Boolean = false
    private val categories = arrayOf(Pop(), Science(), Sports(), Rock())


    constructor(player1: Player, player2: Player, logger: Logger = Logger()) : this(logger) {
        addPlayers(player1, player2)
    }

    constructor(player1: Player, player2: Player, player3: Player, logger: Logger = Logger()) : this(logger) {
        addPlayers(player1, player2, player3)
    }

    constructor(player1: Player, player2: Player, player3: Player, player4: Player, logger: Logger = Logger()) : this(logger) {
        addPlayers(player1, player2, player3, player4)
    }

    constructor(player1: Player, player2: Player, player3: Player, player4: Player, player5: Player, logger: Logger = Logger()) : this(logger) {
        addPlayers(player1, player2, player3, player4, player5)
    }

    constructor(player1: Player, player2: Player, player3: Player, player4: Player, player5: Player, player6: Player, logger: Logger = Logger()) : this(logger) {
        addPlayers(player1, player2, player3, player4, player5, player6)
    }

    private fun addPlayers(vararg players: Player) {
        players.forEach { add(it) }
    }

    private fun add(player: Player): Boolean {
        players.add(player)

        log(player.name + " was added")
        log("They are player number " + players.size)
        return true
    }

    fun roll(roll: Int) {
        log(currentPlayer().name + " is the current player")
        log("They have rolled a " + roll)

        if (currentPlayer().isInPenaltyBox()) {
            if (rolledAnOddNumber(roll)) {
                isGettingOutOfPenaltyBox = true

                log(currentPlayer().name + " is getting out of the penalty box")
                currentPlayerMoveForward(roll)
                askQuestion()
            } else {
                log(currentPlayer().name + " is not getting out of the penalty box")
                isGettingOutOfPenaltyBox = false
            }

        } else {

            currentPlayerMoveForward(roll)
            askQuestion()
        }

    }

    private fun currentPlayerMoveForward(roll: Int) {
        currentPlayer().moveForward(roll)

        log(currentPlayer().name
                + "'s new location is "
                + currentPlayerPlace())
        log("The category is " + currentCategory().name())
    }

    private fun rolledAnOddNumber(roll: Int) = roll % 2 != 0

    private fun askQuestion() {
        log(currentCategory().question())
    }

    private fun currentCategory(): Category = categories[currentPlayerPlace() % categories.size]

    private fun currentPlayerPlace() = currentPlayer().place

    fun wasCorrectlyAnswered(): Boolean {
        if (currentPlayer().isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                currentPlayerScoresAPoint()

                val winner = gameContinues()
                nextPlayer()

                return winner
            } else {
                nextPlayer()
                return true
            }


        } else {

            currentPlayerScoresAPoint()

            val winner = gameContinues()
            nextPlayer()

            return winner
        }
    }

    private fun currentPlayerScoresAPoint() {
        log("Answer was correct!!!!")
        currentPlayer().purse++
        log(currentPlayer().name
                + " now has "
                + currentPlayer().purse
                + " Gold Coins.")
    }

    fun wrongAnswer(): Boolean {
        log("Question was incorrectly answered")
        log(currentPlayer().name + " was sent to the penalty box")
        currentPlayer().goesToPenaltyBox()

        nextPlayer()
        return true
    }

    private fun nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
    }

    private fun log(message: String) {
        logger.log(message)
    }

    private fun gameContinues(): Boolean {
        return !currentPlayer().hasWon()
    }

    private fun currentPlayer() = players[currentPlayerIndex]
}