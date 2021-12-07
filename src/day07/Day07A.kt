package day07

import getFileReader
import kotlin.math.abs
import kotlin.math.min

fun main() {
    val reader = getFileReader("src/day07/input.txt")
    val positions = reader.readLine().split(",").map { it.toInt() }
    var minimumCost = Int.MAX_VALUE
    for (position in positions) {
        var currentCost = 0
        for (destination in positions) {
            currentCost += abs(position - destination)
        }
        minimumCost = min(minimumCost, currentCost)
    }
    println(minimumCost)
}