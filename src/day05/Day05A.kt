package day05

import getFileReader

fun main() {
    val reader = getFileReader("src/day05/input.txt")
    val map = HashMap<Pair<Int, Int>, Int>()
    while (true) {
        val line = reader.readLine() ?: break
        if (line.isEmpty()) break
        val endPoints = line.split(" -> ")
        val pointOne = endPoints[0].split(",").map {
            it.toInt()
        }
        val pointTwo = endPoints[1].split(",").map {
            it.toInt()
        }
        if (pointOne[0] == pointTwo[0]) {
            val min = pointOne[1].coerceAtMost(pointTwo[1])
            val max = pointOne[1].coerceAtLeast(pointTwo[1])
            for (y in min until max + 1) {
                val currPair = Pair(pointOne[0], y)
                map[currPair] = (map[currPair] ?: 0) + 1
            }
        }
        if (pointOne[1] == pointTwo[1]) {
            val min = pointOne[0].coerceAtMost(pointTwo[0])
            val max = pointOne[0].coerceAtLeast(pointTwo[0])
            for (x in min until max + 1) {
                val currPair = Pair(x, pointOne[1])
                map[currPair] = (map[currPair] ?: 0) + 1
            }
        }
    }
    var answer = 0
    for (value in map.values) {
        if (value > 1) ++answer
    }
    println(answer)
}