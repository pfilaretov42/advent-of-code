package dev.pfilaretov42.aoc.aoc2023.day2

import java.io.File

fun main() {
    val inputFile = File("input/2023/day2/input")
//    partOne(inputFile)
    partTwo(inputFile)

}

fun partTwo(inputFile: File) {
    var totalSum = 0
    inputFile.forEachLine { line ->
        val map = mutableMapOf(
            "red" to 0,
            "green" to 0,
            "blue" to 0,
        )

        "([0-9]+ [a-z]+)+".toRegex()
            .findAll(line)
            .forEach {
                val colourNumberPair = it.value.split(" ")
                val colour = colourNumberPair[1]
                val qubesNumber = colourNumberPair[0].toInt()
                map.computeIfPresent(colour) { _, value -> if (qubesNumber > value) qubesNumber else value }
            }

        totalSum += map.values.reduce { accumulator, element -> accumulator * element }
    }

    println(totalSum)
}

fun partOne(inputFile: File) {
    val limits = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    var gameIdSum = 0;
    inputFile.forEachLine { line ->
        val noImpossibleGames = "([0-9]+ [a-z]+)+".toRegex()
            .findAll(line)
            .none {
                val colorNumberPair = it.value.split(" ")
                colorNumberPair[0].toInt() > limits[colorNumberPair[1]]!!
            }

        if (noImpossibleGames) {
            val gameId = "Game ([0-9]{1,3})".toRegex()
                .findAll(line)
                .toList()[0]
                .groupValues[1].toInt()
            println("Adding gameId=$gameId")
            gameIdSum += gameId
        }
    }

    println(gameIdSum)
}
