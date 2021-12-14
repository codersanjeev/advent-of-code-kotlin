package day14

import getFileReader

fun main() {
    val reader = getFileReader("src/day14/input.txt")
    val first = reader.readLine()
    var frequencyMap = mutableMapOf<String, Long>()
    first.windowed(2).forEach {
        frequencyMap[it] = (frequencyMap[it] ?: 0) + 1
    }
    reader.readLine()
    val transformations = mutableListOf<Pair<String, String>>()
    while (true) {
        val transformation = reader.readLine()
        if (transformation.isNullOrEmpty()) break
        val (u, v) = transformation.split(" -> ")
        transformations.add(Pair(u, v))
    }
    repeat(40) {
        val newFrequencyMap = mutableMapOf<String, Long>()
        frequencyMap = frequencyMap.map { (key, value) ->
            if (transformations.any { it.first == key }) {
                newFrequencyMap.merge(
                    key[0] + transformations.first { it.first == key }.second,
                    value,
                    Long::plus
                )
                newFrequencyMap.merge(
                    transformations.first { it.first == key }.second + key[1],
                    value,
                    Long::plus
                )
                key to 0L
            } else {
                key to value
            }
        }.toMap().toMutableMap()
        newFrequencyMap.forEach {
            frequencyMap.merge(it.key, it.value) { a, b -> a + b }
        }
    }
    val finalMap = mutableMapOf<Char, Long>()
    frequencyMap.forEach {
        finalMap.merge(it.key.first(), it.value, Long::plus)
    }
    finalMap.merge(first.last(), 1, Long::plus)
    println(finalMap.values.maxOrNull()!! - finalMap.values.minOrNull()!!)
}