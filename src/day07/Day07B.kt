package day07

import getFileReader
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = getFileReader("src/day07/input.txt")
    val positions = reader.readLine().split(",").map { it.toInt() }
    var minimumCost = Int.MAX_VALUE
    var (max, min) = listOf(Int.MIN_VALUE, Int.MAX_VALUE)
    for (position in positions) {
        max = max(max, position)
        min = min(min, position)
    }
    for (destination in min until max + 1) {
        var currentCost = 0
        for (position in positions) {
            val distance = abs(position - destination)
            val cost = (distance * (distance + 1)) / 2
            currentCost += cost
        }
        minimumCost = min(minimumCost, currentCost)
    }
    println(minimumCost)
}