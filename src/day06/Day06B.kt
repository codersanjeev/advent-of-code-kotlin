package day06

import getFileReader

fun main() {
    val reader = getFileReader("src/day06/input.txt")
    val fishes = reader.readLine().split(",").map { it.toLong() }
    var fishesMap = HashMap<Long, Long>()
    for (fish in fishes) {
        fishesMap[fish] = (fishesMap[fish] ?: 0) + 1
    }
    repeat(256) {
        val newFishesMap = HashMap<Long, Long>()
        fishesMap.forEach { (fish, fishCount) ->
            if (fish == 0L) {
                newFishesMap[6] = (newFishesMap[6] ?: 0) + fishCount
                newFishesMap[8] = (newFishesMap[8] ?: 0) + fishCount
            } else {
                newFishesMap[fish - 1] = (newFishesMap[fish - 1] ?: 0) + fishCount
            }
        }
        fishesMap = newFishesMap
    }
    println(fishesMap.values.sum())
}