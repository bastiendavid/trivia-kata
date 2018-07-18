package com.adaptionsoft.games.uglytrivia

abstract class Category {
    private var index = 0

    abstract fun name(): String

    fun question(): String = "${name()} Question ${index++}"
}