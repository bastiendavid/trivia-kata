package com.adaptionsoft.games.uglytrivia

import org.junit.Test

class GameTest {

    @Test
    fun roll_asks_the_player_a_question() {
        val logger = Logger.get()
        val game = Game("player1", "player2")
        game.roll(1)
        assert(logger.logs.contains("player1 is the current player"))
        assert(logger.logs.contains("Science Question 0"))
    }

    @Test
    fun test() {
        val game = Game("player1", "player2")
        game.roll(3)
        game.wasCorrectlyAnswered()
        game.roll(3)
        game.wasCorrectlyAnswered()
    }

}