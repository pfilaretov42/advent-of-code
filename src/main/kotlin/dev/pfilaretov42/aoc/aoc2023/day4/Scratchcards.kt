package dev.pfilaretov42.aoc.aoc2023.day4

import java.io.File
import kotlin.math.pow

fun main() {
    val inputFile = File("input/2023/day4/input")
//    partOne(inputFile)
    partTwo(inputFile)
}

fun partTwo(inputFile: File) {
    val lineRegex = ":\\s+".toRegex()
    val cardRegex = "\\s+\\|\\s+".toRegex()
    val numbersRegex = "\\s+".toRegex()

    val cardsCountMap = mutableMapOf<Int, Int>()

    inputFile.forEachLine { line ->
        val cardAndNumbers = line.split(lineRegex)

        val cardNumber = cardAndNumbers[0].split(numbersRegex)[1].toInt()
        val cardsCount = cardsCountMap.merge(cardNumber, 1) { oldValue, _ -> oldValue + 1 }!!

        val numbers = cardAndNumbers[1].split(cardRegex)
        val winningNumbers = numbers[0].split(numbersRegex).toSet()
        val numbersToTest = numbers[1].split(numbersRegex).toSet()

        val winningCount = numbersToTest.filter { winningNumbers.contains(it) }.size

        IntRange(cardNumber + 1, cardNumber + winningCount).forEach { additionalCardNumber ->
            cardsCountMap.merge(additionalCardNumber, 1) { oldValue, _ -> oldValue + cardsCount }
        }

        println(cardsCountMap.values.sum())
    }

    println(cardsCountMap.values.sum())
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
