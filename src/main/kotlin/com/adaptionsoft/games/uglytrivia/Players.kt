package com.adaptionsoft.games.uglytrivia

class Players(private val game: Game) {

    private val players = ArrayList<Player>()
    private var currentPlayerIndex: Int = 0

    fun join(vararg players: String) {
        players.forEach { this.join(Player(it, game)) }
    }

    private fun join(player: Player) {
        players.add(player)
        Logger.get().log(player.name + " was added")
        Logger.get().log("They are player number " + players.size)
    }

    fun next() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
    }

    fun current(): Player = players[currentPlayerIndex]

}
