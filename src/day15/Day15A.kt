package day15

import getFileReader
import kotlin.math.min

fun main() {
    val reader = getFileReader("src/day15/input.txt")
    val riskBoard = mutableListOf<List<Int>>()
    val dp = mutableListOf<MutableList<Int>>()
    var maxCost = 0
    while (true) {
        val row = reader.readLine()
        if (row.isNullOrEmpty()) break
        riskBoard.add(row.map { it.digitToInt() })
        dp.add(row.map { -1 }.toMutableList())
        maxCost += row.map { it.digitToInt() }.sum()
    }
    fun findMinCost(row: Int, col: Int): Int {
        if (row < 0 || col < 0 || row >= riskBoard.size || col >= riskBoard[0].size) {
            return maxCost
        }
        if (dp[row][col] != -1) return dp[row][col]
        if (row == riskBoard.size - 1 && col == riskBoard[0].size - 1) {
            dp[row][col] = riskBoard[row][col]
        } else {
            val minCost = min(findMinCost(row + 1, col), findMinCost(row, col + 1))
            dp[row][col] = riskBoard[row][col] + minCost
        }
        return dp[row][col]
    }

    val minCost = findMinCost(0, 0)
    println(minCost - riskBoard[0][0])
}