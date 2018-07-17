package com.adaptionsoft.games.uglytrivia

class Player(val name: String) {
    fun hasWon(): Boolean = purse == 6

    var purse: Int = 0

}
