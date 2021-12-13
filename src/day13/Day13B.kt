package day13

import getFileReader

fun main() {
    val reader = getFileReader("src/day13/input.txt")
    var points = hashMapOf<Pair<Int, Int>, Boolean>()
    while (true) {
        val point = reader.readLine()
        if (point.isNullOrEmpty()) break
        val (x, y) = point.split(",").map { it.toInt() }
        points[Pair(x, y)] = true
    }
    while (true) {
        val line = reader.readLine()
        if (line.isNullOrEmpty()) break
        val (a, b) = line.split(" ").last().split("=")
        val newPoints = hashMapOf<Pair<Int, Int>, Boolean>()
        val c = b.toInt()
        if (a == "x") {
            points.keys.forEach {
                if (it.first < c) {
                    newPoints[it] = true
                } else {
                    newPoints[Pair(c - (it.first - c), it.second)] = true
                }
            }
        } else {
            points.keys.forEach {
                if (it.second < c) {
                    newPoints[it] = true
                } else {
                    newPoints[Pair(it.first, c - (it.second - c))] = true
                }
            }
        }
        points = newPoints
    }
    val x = points.keys.maxOf { it.first }
    val y = points.keys.maxOf { it.second }
    var answer = ""
    for (b in 0 until y + 1) {
        for (a in 0 until x + 1) {
            answer += if (points.containsKey(Pair(a, b))) "x" else " "
        }
        println(answer)
        answer = ""
    }
}