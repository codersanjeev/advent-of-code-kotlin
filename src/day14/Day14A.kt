package day14

import getFileReader

fun main() {
    val reader = getFileReader("src/day14/input.txt")
    var first = reader.readLine()
    reader.readLine()
    val transformations = mutableListOf<Pair<String, String>>()
    while (true) {
        val transformation = reader.readLine()
        if (transformation.isNullOrEmpty()) break
        val (u, v) = transformation.split(" -> ")
        transformations.add(Pair(u, v))
    }
    repeat(10) {
        var index = 1
        while (index < first.length) {
            val currString = first.substring(index - 1, index + 1)
            val transformation = transformations.first { it.first == currString }.second
            first = first.substring(0, index) + transformation + first.substring(index)
            index += 2
        }
    }
    val frequencyMap = mutableMapOf<Char, Int>()
    first.forEach { frequencyMap[it] = (frequencyMap[it] ?: 0) + 1 }
    println(frequencyMap.maxOf { it.value } - frequencyMap.minOf { it.value })
}