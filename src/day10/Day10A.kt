package day10

import getFileReader

fun main() {
    val reader = getFileReader("src/day10/input.txt")
    var totalPoints = 0
    val pointsTable = mapOf(Pair(')', 3), Pair(']', 57), Pair('}', 1197), Pair('>', 25137))
    val goodPairs = mapOf(Pair('(', ')'), Pair('{', '}'), Pair('[', ']'), Pair('<', '>'))
    fun calculatePoints(stack: MutableList<Char>, ch: Char, points: Int): Int {
        return if (ch != goodPairs[stack.last()]) {
            pointsTable[ch] ?: 0
        } else {
            stack.removeLast()
            0
        }
    }
    while (true) {
        val currentLine = reader.readLine()
        if (currentLine.isNullOrEmpty()) break
        var currentPoints = 0
        val stack = mutableListOf<Char>()
        for (ch in currentLine) {
            if (listOf('(', '{', '[', '<').contains(ch)) {
                stack.add(ch)
            } else if (stack.isEmpty()) {
                break
            } else {
                val points = calculatePoints(stack, ch, currentPoints)
                if (points != 0) {
                    currentPoints += points
                    break
                }
            }
        }
        totalPoints += currentPoints
    }
    println(totalPoints)
}