package com.adaptionsoft.games.uglytrivia

class Game {

    var players = Players()
    private val categories = arrayOf(Pop(), Science(), Sports(), Rock())


    constructor(player1: Player, player2: Player) {
        addPlayers(player1, player2)
    }

    constructor(player1: Player, player2: Player, player3: Player) {
        addPlayers(player1, player2, player3)
    }

    constructor(player1: Player, player2: Player, player3: Player, player4: Player) {
        addPlayers(player1, player2, player3, player4)
    }

    constructor(player1: Player, player2: Player, player3: Player, player4: Player, player5: Player) {
        addPlayers(player1, player2, player3, player4, player5)
    }

    constructor(player1: Player, player2: Player, player3: Player, player4: Player, player5: Player, player6: Player) {
        addPlayers(player1, player2, player3, player4, player5, player6)
    }

    private fun addPlayers(vararg players: Player) {
        players.forEach { this.players.add(it) }
    }

    fun roll(roll: Int) {
        log(players.current().name + " is the current player")
        log("They have rolled a " + roll)

        if (players.current().isInPenaltyBox()) {
            if (rolledAnOddNumber(roll)) {
                players.current().canAnswer = true

                log(players.current().name + " is getting out of the penalty box")
                currentPlayerMoveOnBoard(roll)
                askQuestion()
            } else {
                log(players.current().name + " is not getting out of the penalty box")
                players.current().canAnswer = false
            }

        } else {

            currentPlayerMoveOnBoard(roll)
            askQuestion()
        }
    }

    private fun currentPlayerMoveOnBoard(roll: Int) {
        players.current().moveOnBoard(roll)
    }

    private fun rolledAnOddNumber(roll: Int) = roll % 2 != 0

    private fun askQuestion() {
        log("The category is " + currentCategory().name())
        log(currentCategory().question())
    }

    private fun currentCategory(): Category = categories[currentPlayerPlace() % categories.size]

    private fun currentPlayerPlace() = players.current().place

    fun wasCorrectlyAnswered(): Boolean {
        if (players.current().canAnswer) {
            currentPlayerScoresAPoint()

            val winner = gameContinues()
            players.next()

            return winner
        } else {
            players.next()
            return true
        }
    }

    private fun currentPlayerScoresAPoint() {
        log("Answer was correct!!!!")
        players.current().scoresAPoint()
        log(players.current().name
                + " now has "
                + players.current().numberOfPoints()
                + " Gold Coins.")
    }

    fun wrongAnswer(): Boolean {
        log("Question was incorrectly answered")
        log(players.current().name + " was sent to the penalty box")
        players.current().goesToPenaltyBox()

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