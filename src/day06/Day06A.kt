package day06

import getFileReader

fun main() {
    val reader = getFileReader("src/day06/input.txt")
    var fishes = reader.readLine().split(",").map { it.toInt() }
    repeat(80) {
        val newFishes = mutableListOf<Int>()
        for (fish in fishes) {
            if (fish == 0) {
                newFishes.addAll(listOf(6, 8))
            } else {
                newFishes.add(fish - 1)
            }
        }
        fishes = newFishes
    }
    println(fishes.size)
}