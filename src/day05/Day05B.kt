package day05

import getFileReader
import kotlin.math.abs

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
        val dy = pointTwo[1] - pointOne[1]
        val dx = pointTwo[0] - pointOne[0]
        for (i in 0 until 1 + abs(dx).coerceAtLeast(abs(dy))) {
            var xMove = 0
            var yMove = 0
            if (dx > 0) {
                xMove = 1
            } else if (dx < 0) {
                xMove = -1
            }
            if (dy > 0) {
                yMove = 1
            } else if (dy < 0) {
                yMove = -1
            }
            val currPoint = Pair(pointOne[0] + (xMove * i), pointOne[1] + (yMove * i))
            map[currPoint] = (map[currPoint] ?: 0) + 1
        }
    }
    val answer = map.values.filter {
        it > 1
    }.size
    println(answer)
}