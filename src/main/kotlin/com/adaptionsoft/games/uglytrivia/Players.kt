package com.adaptionsoft.games.uglytrivia

class Players {

    private val players = ArrayList<Player>()
    private var currentPlayerIndex: Int = 0

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
        players.forEach { this.add(it) }
    }

    private fun add(player: Player) {
        players.add(player)
        Logger.get().log(player.name + " was added")
        Logger.get().log("They are player number " + players.size)
    }

    fun next() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
    }

    fun current(): Player = players[currentPlayerIndex]

}
