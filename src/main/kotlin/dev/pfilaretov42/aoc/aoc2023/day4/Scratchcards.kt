package dev.pfilaretov42.aoc.aoc2023.day4

import java.io.File
import kotlin.math.pow

fun main() {
    val inputFile = File("input/2023/day4/input")
    partOne(inputFile)
//    partTwo(inputFile)
}

fun partOne(inputFile: File) {
    var total = 0
    val lineRegex = ":\\s+".toRegex()
    val cardRegex = "\\s+\\|\\s+".toRegex()
    val numbersRegex = "\\s+".toRegex()
    inputFile.forEachLine { line ->
        val numbers = line.split(lineRegex)[1].split(cardRegex)
        val winningNumbers = numbers[0].split(numbersRegex).toSet()
        val numbersToTest = numbers[1].split(numbersRegex).toSet()

        val count = numbersToTest.filter { winningNumbers.contains(it) }.size
        total += 2.0.pow((count - 1).toDouble()).toInt()
    }

    println(total)
}
