package com.adaptionsoft.games.uglytrivia

import java.util.*

class Game(private val logger: Logger = Logger()) {

    var players = ArrayList<Player>()

    var popQuestions = LinkedList<String>()
    var scienceQuestions = LinkedList<String>()
    var sportsQuestions = LinkedList<String>()
    var rockQuestions = LinkedList<String>()

    var currentPlayer = 0
    var isGettingOutOfPenaltyBox: Boolean = false

    init {
        for (i in 0..49) {
            popQuestions.addLast("Pop Question " + i)
            scienceQuestions.addLast("Science Question " + i)
            sportsQuestions.addLast("Sports Question " + i)
            rockQuestions.addLast("Rock Question " + i)
        }
    }

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

    fun howManyPlayers(): Int {
        return players.size
    }

    fun roll(roll: Int) {
        log(currentPlayer().name + " is the current player")
        log("They have rolled a " + roll)

        if (currentPlayer().isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true

                log(currentPlayer().name + " is getting out of the penalty box")
                currentPlayer().moveForward(roll)

                log(currentPlayer().name
                        + "'s new location is "
                        + currentPlayerPlace())
                log("The category is " + currentCategory())
                askQuestion()
            } else {
                log(currentPlayer().name + " is not getting out of the penalty box")
                isGettingOutOfPenaltyBox = false
            }

        } else {

            currentPlayer().moveForward(roll)

            log(currentPlayer().name
                    + "'s new location is "
                    + currentPlayerPlace())
            log("The category is " + currentCategory())
            askQuestion()
        }

    }

    private fun askQuestion() {
        if (currentCategory() === "Pop")
            log(popQuestions.removeFirst())
        if (currentCategory() === "Science")
            log(scienceQuestions.removeFirst())
        if (currentCategory() === "Sports")
            log(sportsQuestions.removeFirst())
        if (currentCategory() === "Rock")
            log(rockQuestions.removeFirst())
    }

    private fun currentCategory(): String {
        if (currentPlayerPlace() == 0) return "Pop"
        if (currentPlayerPlace() == 4) return "Pop"
        if (currentPlayerPlace() == 8) return "Pop"
        if (currentPlayerPlace() == 1) return "Science"
        if (currentPlayerPlace() == 5) return "Science"
        if (currentPlayerPlace() == 9) return "Science"
        if (currentPlayerPlace() == 2) return "Sports"
        if (currentPlayerPlace() == 6) return "Sports"
        if (currentPlayerPlace() == 10) return "Sports"
        return "Rock"
    }

    private fun currentPlayerPlace() = currentPlayer().place

    fun wasCorrectlyAnswered(): Boolean {
        if (currentPlayer().isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                log("Answer was correct!!!!")
                currentPlayer().purse++
                log(currentPlayer().name
                        + " now has "
                        + currentPlayer().purse
                        + " Gold Coins.")

                val winner = didPlayerWin()
                currentPlayer++
                if (currentPlayer == players.size) currentPlayer = 0

                return winner
            } else {
                currentPlayer++
                if (currentPlayer == players.size) currentPlayer = 0
                return true
            }


        } else {

            log("Answer was corrent!!!!")
            currentPlayer().purse++
            log((currentPlayer().name
                    + " now has "
                    + currentPlayer().purse
                    + " Gold Coins."))

            val winner = didPlayerWin()
            currentPlayer++
            if (currentPlayer == players.size) currentPlayer = 0

            return winner
        }
    }

    fun wrongAnswer(): Boolean {
        log("Question was incorrectly answered")
        log(currentPlayer().name + " was sent to the penalty box")
        currentPlayer().goesToPenaltyBox()

        currentPlayer++
        if (currentPlayer == players.size) currentPlayer = 0
        return true
    }

    private fun log(message: String) {
        logger.log(message)
    }

    private fun didPlayerWin(): Boolean {
        return !currentPlayer().hasWon()
    }

    private fun currentPlayer() = players[currentPlayer]
}