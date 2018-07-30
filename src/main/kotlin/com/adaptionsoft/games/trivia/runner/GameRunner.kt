package com.adaptionsoft.games.trivia.runner

import com.adaptionsoft.games.uglytrivia.Game
import com.adaptionsoft.games.uglytrivia.Logger
import com.adaptionsoft.games.uglytrivia.Player
import com.adaptionsoft.games.uglytrivia.Players
import java.util.*

fun main(args: Array<String>) {
    val aGame = Game("Chet", "Pat", "Sue")

    val rand = random(args)

    do {
        aGame.roll(diceRoll(rand))

        if (playerFailsToAnswer(rand)) {
            aGame.wrongAnswer()
        } else {
            aGame.wasCorrectlyAnswered()
        }
    } while (!aGame.isOver())

}

private fun playerFailsToAnswer(rand: Random) = rand.nextInt(9) == 7

private fun diceRoll(rand: Random) = rand.nextInt(5) + 1

private fun random(args: Array<String>): Random {
    return when (args.size) {
        1 -> Random(args[0].toLong())
        else -> Random()
    }
}
