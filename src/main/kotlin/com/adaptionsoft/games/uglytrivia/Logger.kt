package com.adaptionsoft.games.uglytrivia

class Logger private constructor() {

    companion object {
        @JvmStatic
        private val logger: Logger = Logger()

        @JvmStatic
        fun get(): Logger = logger
    }

    val logs = ArrayList<String>()

    fun log(message: String) {
        println(message)
        logs.add(message)
    }

    fun logsAsString(): String {
        return logs.reduce { acc, s -> "$acc\n$s" }
    }

    fun clear() {
        logs.clear()
    }
}