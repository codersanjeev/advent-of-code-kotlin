package day10

import getFileReader

fun main() {
    val reader = getFileReader("src/day10/input.txt")
    val scoresTable = mapOf(Pair('(', 1L), Pair('[', 2L), Pair('{', 3L), Pair('<', 4L))
    val goodPairs = mapOf(Pair('(', ')'), Pair('{', '}'), Pair('[', ']'), Pair('<', '>'))
    val scores = mutableListOf<Long>()
    fun checkValidity(stack: MutableList<Char>, ch: Char): Boolean {
        return if (ch != goodPairs[stack.last()]) {
            true
        } else {
            stack.removeLast()
            false
        }
    }
    while (true) {
        val currentLine = reader.readLine()
        if (currentLine.isNullOrEmpty()) break
        val stack = mutableListOf<Char>()
        var good = false
        for (ch in currentLine) {
            if (listOf('(', '{', '[', '<').contains(ch)) {
                stack.add(ch)
            } else if (stack.isEmpty()) {
                break
            } else {
                if (checkValidity(stack, ch)) {
                    good = true
                    break
                }
            }
        }
        if (!good) {
            var score = 0L
            for (ch in stack.reversed()) {
                score *= 5L
                score += scoresTable[ch] ?: 0
            }
            scores.add(score)
        }
    }
    println(scores.sorted()[scores.size / 2])
}