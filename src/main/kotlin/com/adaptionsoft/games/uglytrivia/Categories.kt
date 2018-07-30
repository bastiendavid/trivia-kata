package com.adaptionsoft.games.uglytrivia

class Categories {
    private val categories = arrayOf(Pop(), Science(), Sports(), Rock())

    fun count(): Int = categories.size

    fun at(place: Int): Category = categories[place % categories.size]
}