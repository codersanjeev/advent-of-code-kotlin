package day08

import getFileReader

fun main() {
    val reader = getFileReader("src/day08/input.txt")
    var answer = 0
    while (true) {
        val currentLine = reader.readLine()
        if (currentLine.isNullOrEmpty()) break
        val words = currentLine.split(" | ").last()
        for (word in words.split(" ")) {
            if (word.length in listOf(2, 3, 4, 7)) {
                ++answer
            }
        }
    }
    println(answer)
}