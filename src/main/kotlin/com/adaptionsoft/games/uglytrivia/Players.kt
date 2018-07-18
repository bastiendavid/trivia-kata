package com.adaptionsoft.games.uglytrivia

class Players(private val logger: Logger = Logger()) {

    private val players = ArrayList<Player>()
    private var currentPlayerIndex: Int = 0

    fun add(player: Player) {
        players.add(player)
        logger.log(player.name + " was added")
        logger.log("They are player number " + players.size)
    }

    fun next() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
    }

    fun current(): Player = players[currentPlayerIndex]

}
