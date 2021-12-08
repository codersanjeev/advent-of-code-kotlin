package day08

import getFileReader

/**
 *  aaaa
 * b    c
 * b    c
 *  dddd
 * e    f
 * e    f
 *  gggg
 */
fun main() {
    val reader = getFileReader("src/day08/input.txt")
    var answer = 0
    while (true) {
        val currentLine = reader.readLine()
        if (currentLine.isNullOrEmpty()) break
        val (first, last) = currentLine.split(" | ")
        val code = Array(10) { "" }
        for (word in first.split(" ")) {
            when (word.length) {
                2 -> code[1] = word.toMutableList().sorted().toString()
                3 -> code[7] = word.toMutableList().sorted().toString()
                4 -> code[4] = word.toMutableList().sorted().toString()
                7 -> code[8] = word.toMutableList().sorted().toString()
            }
        }
        for (word in first.split(" ")) {
            if (word.length == 5 && word.toSet().intersect(code[4].toSet()).size == 2) {
                code[2] = word.toMutableList().sorted().toString()
            }
        }
        for (word in first.split(" ")) {
            if (word.length == 5) {
                when (word.toSet().intersect(code[2].toSet()).size) {
                    3 -> code[5] = word.toMutableList().sorted().toString()
                    4 -> code[3] = word.toMutableList().sorted().toString()
                }
            }
        }
        for (word in first.split(" ")) {
            if (word.length == 6) {
                if (word.toSet().intersect(code[3].toSet()).size == 5) {
                    code[9] = word.toMutableList().sorted().toString()
                } else if (word.toSet().intersect(code[1].toSet()).size == 2) {
                    code[0] = word.toMutableList().sorted().toString()
                } else {
                    code[6] = word.toMutableList().sorted().toString()
                }
            }
        }
        val codeMapping = (0 until 10).associateBy { code[it] }
        val currentAnswer = mutableListOf<Int>()
        for (word in last.split(" ")) {
            currentAnswer.add(codeMapping[word.toMutableList().sorted().toString()] ?: 0)
        }
        answer += currentAnswer.joinToString("") {
            it.toString()
        }.toInt()
    }
    println(answer)
}