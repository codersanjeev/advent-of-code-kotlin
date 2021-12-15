package day15

import getFileReader
import java.util.*

data class Node(val row: Int, val col: Int, val dist: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return if (this.dist < other.dist) {
            -1
        } else {
            1
        }
    }
}

fun main() {
    val reader = getFileReader("src/day15/input.txt")
    val riskBoard = mutableListOf<List<Int>>()
    while (true) {
        val row = reader.readLine()
        if (row.isNullOrEmpty()) break
        riskBoard.add(row.map { it.digitToInt() })
    }
    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)
    val dp = MutableList(5 * riskBoard.size) {
        MutableList(5 * riskBoard[0].size) { -1 }
    }
    val bfsQueue: Queue<Node> = PriorityQueue()
    bfsQueue.add(Node(0, 0, 0))
    while (bfsQueue.isNotEmpty()) {
        val currNode = bfsQueue.remove()
        if (currNode.row < 0 || currNode.col < 0 || currNode.row >= 5 * riskBoard.size || currNode.col >= 5 * riskBoard[0].size) {
            continue
        }
        var currValue =
            riskBoard[currNode.row % riskBoard.size][currNode.col % riskBoard[0].size] + currNode.row / riskBoard.size + currNode.col / riskBoard[0].size
        while (currValue > 9) currValue -= 9
        val currCost = currNode.dist + currValue
        if (dp[currNode.row][currNode.col] == -1 || currCost < dp[currNode.row][currNode.col]) {
            dp[currNode.row][currNode.col] = currCost
        } else {
            continue
        }
        if (currNode.row == 5 * riskBoard.size - 1 && currNode.col == 5 * riskBoard[0].size - 1) {
            break
        }
        repeat(4) {
            val newRow = currNode.row + dx[it]
            val newCol = currNode.col + dy[it]
            bfsQueue.add(Node(newRow, newCol, dp[currNode.row][currNode.col]))
        }
    }
    println(dp.last().last() - riskBoard.first().first())
}