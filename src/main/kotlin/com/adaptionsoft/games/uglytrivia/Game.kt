package com.adaptionsoft.games.uglytrivia

import java.util.*

class Game(private val logger: Logger = Logger()) {

    var players = ArrayList<Player>()
    var places = IntArray(6)
    var purses = IntArray(6)
    var inPenaltyBox = BooleanArray(6)

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
        places[howManyPlayers()] = 0
        purses[howManyPlayers()] = 0
        inPenaltyBox[howManyPlayers()] = false

        log(player.name + " was added")
        log("They are player number " + players.size)
        return true
    }

    fun howManyPlayers(): Int {
        return players.size
    }

    fun roll(roll: Int) {
        log(players[currentPlayer].name + " is the current player")
        log("They have rolled a " + roll)

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true

                log(players[currentPlayer].name + " is getting out of the penalty box")
                places[currentPlayer] = places[currentPlayer] + roll
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12

                log(players[currentPlayer].name
                        + "'s new location is "
                        + places[currentPlayer])
                log("The category is " + currentCategory())
                askQuestion()
            } else {
                log(players[currentPlayer].name + " is not getting out of the penalty box")
                isGettingOutOfPenaltyBox = false
            }

        } else {

            places[currentPlayer] = places[currentPlayer] + roll
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12

            log(players[currentPlayer].name
                    + "'s new location is "
                    + places[currentPlayer])
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
        if (places[currentPlayer] == 0) return "Pop"
        if (places[currentPlayer] == 4) return "Pop"
        if (places[currentPlayer] == 8) return "Pop"
        if (places[currentPlayer] == 1) return "Science"
        if (places[currentPlayer] == 5) return "Science"
        if (places[currentPlayer] == 9) return "Science"
        if (places[currentPlayer] == 2) return "Sports"
        if (places[currentPlayer] == 6) return "Sports"
        if (places[currentPlayer] == 10) return "Sports"
        return "Rock"
    }

    fun wasCorrectlyAnswered(): Boolean {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                log("Answer was correct!!!!")
                purses[currentPlayer]++
                log(players[currentPlayer].name
                        + " now has "
                        + purses[currentPlayer]
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
            purses[currentPlayer]++
            log((players[currentPlayer].name
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins."))

            val winner = didPlayerWin()
            currentPlayer++
            if (currentPlayer == players.size) currentPlayer = 0

            return winner
        }
    }

    fun wrongAnswer(): Boolean {
        log("Question was incorrectly answered")
        log(players[currentPlayer].name + " was sent to the penalty box")
        inPenaltyBox[currentPlayer] = true

        currentPlayer++
        if (currentPlayer == players.size) currentPlayer = 0
        return true
    }

    private fun log(message: String) {
        logger.log(message)
    }

    private fun didPlayerWin(): Boolean {
        return purses[currentPlayer] != 6
    }
}