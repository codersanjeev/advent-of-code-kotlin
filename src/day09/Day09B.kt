package day09

import getFileReader
import java.util.*

fun main() {
    val reader = getFileReader("src/day09/input.txt")
    val heightMap = mutableListOf<String>()
    while (true) {
        val currentRow = reader.readLine()
        if (currentRow.isNullOrEmpty()) break
        heightMap.add(currentRow)
    }
    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)
    val visited = mutableSetOf<Pair<Int, Int>>()
    val allSizes = mutableListOf<Int>()
    for (rowIndex in heightMap.indices) {
        for (columnIndex in heightMap[rowIndex].indices) {
            if (!visited.contains(Pair(rowIndex, columnIndex)) && heightMap[rowIndex][columnIndex] != '9') {
                var currentSize = 0
                val bfsQueue: Queue<Pair<Int, Int>> = LinkedList()
                bfsQueue.add(Pair(rowIndex, columnIndex))
                while (bfsQueue.isNotEmpty()) {
                    val currentPair = bfsQueue.remove()
                    if (visited.contains(currentPair)) continue
                    visited.add(currentPair)
                    ++currentSize
                    for (k in 0 until 4) {
                        val (nx, ny) = listOf(currentPair.first + dx[k], currentPair.second + dy[k])
                        if (heightMap.indices.contains(nx) && heightMap[nx].indices.contains(ny)) {
                            if (heightMap[nx][ny] != '9') {
                                bfsQueue.add(Pair(nx, ny))
                            }
                        }
                    }
                }
                allSizes.add(currentSize)
            }
        }
    }
    println(allSizes.sorted().takeLast(3).reduce { a, b -> a * b })
}