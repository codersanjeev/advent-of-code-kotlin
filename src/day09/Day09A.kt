package day09

import getFileReader

fun main() {
    val reader = getFileReader("src/day09/input.txt")
    val heightMap = mutableListOf<String>()
    while (true) {
        val currentRow = reader.readLine()
        if (currentRow.isNullOrEmpty()) break
        heightMap.add(currentRow)
    }
    var answer = 0
    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)
    for (rowIndex in heightMap.indices) {
        for (columnIndex in heightMap[rowIndex].indices) {
            var lowPoint = true
            for (k in 0 until 4) {
                val (nx, ny) = listOf(rowIndex + dx[k], columnIndex + dy[k])
                if (heightMap.indices.contains(nx) && heightMap[nx].indices.contains(ny)) {
                    if (heightMap[nx][ny] <= heightMap[rowIndex][columnIndex]) {
                        lowPoint = false
                    }
                }
            }
            if (lowPoint) answer += heightMap[rowIndex][columnIndex].digitToInt() + 1
        }
    }
    println(answer)
}