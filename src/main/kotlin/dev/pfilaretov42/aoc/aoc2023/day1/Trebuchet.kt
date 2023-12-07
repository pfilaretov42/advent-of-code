package dev.pfilaretov42.aoc.aoc2023.day1

import java.io.File


fun main() {
    val calibrationFile = File("input/2023/day1/input")
//    partOne(calibrationFile)
    partTwo(calibrationFile)

}

fun partTwo(file: File) {
    var total = 0
    val map = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )
    file.forEachLine { line ->
        val letterDigits =
            ("one".toRegex().findAll(line) +
                "two".toRegex().findAll(line) +
                "three".toRegex().findAll(line) +
                "four".toRegex().findAll(line) +
                "five".toRegex().findAll(line) +
                "six".toRegex().findAll(line) +
                "seven".toRegex().findAll(line) +
                "eight".toRegex().findAll(line) +
                "nine".toRegex().findAll(line))
                .sortedBy { it.range.first }

        val firstLetter = letterDigits.firstOrNull()
        val lastLetter = letterDigits.lastOrNull()
        val firstLetterDigitPosition = firstLetter?.range?.first ?: Int.MAX_VALUE
        val lastLetterDigitPosition = lastLetter?.range?.first ?: Int.MIN_VALUE

        val digitDigits = "[0-9]".toRegex().findAll(line)
        val firstDigit = digitDigits.firstOrNull()
        val lastDigit = digitDigits.lastOrNull()
        val firstDigitDigitPosition = firstDigit?.range?.first ?: Int.MAX_VALUE
        val lastDigitDigitPosition = lastDigit?.range?.first ?: Int.MIN_VALUE

        val first = if (firstLetterDigitPosition < firstDigitDigitPosition) {
            map[firstLetter!!.value]
        } else {
            firstDigit!!.value
        }

        val last = if (lastLetterDigitPosition > lastDigitDigitPosition) {
            map[lastLetter!!.value]
        } else {
            lastDigit!!.value
        }

        val lineValue = "$first$last".toInt()
        println("lineValue=$lineValue")
        total += lineValue
    }

    println("part two total=$total")
}

fun partOne(file: File) {
    var total = 0
    file.forEachLine { line ->
        val digits = line.filter { it.isDigit() }
        val first = digits.first()
        val last = digits.last()
        val lineValue = "$first$last".toInt()
        println("lineValue=$lineValue")
        total += lineValue
    }

    println("part one total=$total")
}
