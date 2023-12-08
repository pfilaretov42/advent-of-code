package dev.pfilaretov42.aoc.aoc2023.day3

import java.io.File

fun main() {
    val inputFile = File("input/2023/day3/input")
    partOne(inputFile)
//    partTwo(inputFile)
}

fun partOne(inputFile: File) {
    /*
    symbol indexes: [
        set: []
        set: [9, 43, ...]
        set: [...]
    ]

    number indexes: [
        [ [776, (16,18)], [552, (33,35)], [...], ... ]
        [...]
    ]

     */

    var totalSum = 0
    val symbolIndices = mutableListOf<Set<Int>>(emptySet())
    val partNumberCandidateLines = mutableListOf<List<PartNumberCandidate>>()
    inputFile.forEachLine { line ->
        symbolIndices += "[^.0-9]+".toRegex()
            .findAll(line)
            .map {
                it.range.first
            }
            .toSet()

        partNumberCandidateLines += "[0-9]+".toRegex()
            .findAll(line)
            .map {
                PartNumberCandidate(it.value.toInt(), IntRange(it.range.first - 1, it.range.last + 1))
            }
            .toList()
    }
    symbolIndices += emptySet<Int>()

    partNumberCandidateLines.forEachIndexed { index, partNumberCandidates ->
        partNumberCandidates.forEach { candidate ->
            candidate.indices.forEach { numberIndex ->
                if (
                    symbolIndices[index].contains(numberIndex) ||
                    symbolIndices[index + 1].contains(numberIndex) ||
                    symbolIndices[index + 2].contains(numberIndex)
                    ) {
                    totalSum += candidate.value
                }
            }
        }
    }

    println(totalSum)
}

internal data class PartNumberCandidate(
    val value: Int,
    val indices: IntRange,
)
