package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PlayerTest {

    private val game: Game = Game("player1", "player2")


    @Before
    fun setup() {
        Logger.get().clear()
    }

    @Test
    fun player_giving_a_good_answer_scores_a_point() {
        // Given
        val player = game.players.current()
        // When
        player.rolls(2)
        player.gaveAGoodAnswer()

        // Then
        assertTrue(Logger.get().logs.contains("Answer was correct!!!!"))
    }

    @Test
    fun player_in_penalty_box_who_rolls_an_even_number_cannot_score_a_point_with_a_good_answer() {
        // Given
        val player = game.players.current()
        // When
        player.goesToPenaltyBox()
        player.rolls(2)
        player.gaveAGoodAnswer()

        // Then
        assertFalse(Logger.get().logs.contains("Answer was correct!!!!"))
    }

    @Test
    fun player_in_penalty_box_who_rolls_an_odd_number_can_score_a_point_with_a_good_answer() {
        // Given
        val player = game.players.current()
        // When
        player.goesToPenaltyBox()
        player.rolls(3)
        player.gaveAGoodAnswer()

        // Then
        assertTrue(Logger.get().logs.contains("Answer was correct!!!!"))
    }

    @Test
    fun player_in_penalty_box_cannot_answer_if_rolls_an_even_number() {
        // Given
        val player = game.players.current()
        player.goesToPenaltyBox()

        // When
        player.rolls(2)

        // Then
        assertFalse(player.canAttemptToLeavePenaltyBox)
    }

    @Test
    fun player_in_penalty_box_can_answer_if_rolls_an_odd_number() {
        // Given
        val player = game.players.current()
        player.goesToPenaltyBox()

        // When
        player.rolls(1)

        // Then
        assertTrue(player.canAttemptToLeavePenaltyBox)
    }

    @Test
    fun player_in_penalty_box_that_answers_correctly_leaves_penalty_box_and_can_roll_even_numbers_again() {
        // Given
        val player = game.players.current()
        player.goesToPenaltyBox()
        player.rolls(1)
        player.gaveAGoodAnswer()

        // When
        player.rolls(2)

        // Then
        assertTrue(player.canAttemptToLeavePenaltyBox)
    }
}