package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PlayerTest {

    private val player1: Player = Player("player 1")
    private val player2: Player = Player("player 2")
    private val game: Game = Game(Players(player1, player2))

    @Before
    fun setup() {
        Logger.get().clear()
    }

    @Test
    fun player_giving_a_good_answer_scores_a_point() {
        // When
        player1.rolls(game, 2)
        player1.gaveAGoodAnswer()

        // Then
        assertTrue(Logger.get().logs.contains("Answer was correct!!!!"))
    }

    @Test
    fun player_in_penalty_box_who_rolls_an_even_number_cannot_score_a_point_with_a_good_answer() {
        // When
        player1.goesToPenaltyBox()
        player1.rolls(game, 2)
        player1.gaveAGoodAnswer()

        // Then
        assertFalse(Logger.get().logs.contains("Answer was correct!!!!"))
    }

    @Test
    fun player_in_penalty_box_who_rolls_an_odd_number_can_score_a_point_with_a_good_answer() {
        // When
        player1.goesToPenaltyBox()
        player1.rolls(game, 3)
        player1.gaveAGoodAnswer()

        // Then
        assertTrue(Logger.get().logs.contains("Answer was correct!!!!"))
    }

    @Test
    fun player_in_penalty_box_cannot_answer_if_rolls_an_even_number() {
        // Given
        player1.goesToPenaltyBox()

        // When
        player1.rolls(game, 2)

        // Then
        assertFalse(player1.canAttemptToLeavePenaltyBox)
    }

    @Test
    fun player_in_penalty_box_can_answer_if_rolls_an_odd_number() {
        // Given
        player1.goesToPenaltyBox()

        // When
        player1.rolls(game, 1)

        // Then
        assertTrue(player1.canAttemptToLeavePenaltyBox)
    }

    @Test
    fun player_in_penalty_box_that_answers_correctly_leaves_penalty_box_and_can_roll_even_numbers_again() {
        // Given
        player1.goesToPenaltyBox()
        player1.rolls(game, 1)
        player1.gaveAGoodAnswer()

        // When
        player1.rolls(game, 2)

        // Then
        assertTrue(player1.canAttemptToLeavePenaltyBox)
    }
}