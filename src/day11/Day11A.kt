package day11

import getFileReader

fun main() {
    val reader = getFileReader("src/day11/input.txt")
    val board = mutableListOf<MutableList<Int>>()
    var answer = 0
    val dx = listOf(-1, -1, -1, 0, 1, 1, 1, 0)
    val dy = listOf(-1, 0, 1, -1, -1, 0, 1, 1)
    fun flash(row: Int, col: Int) {
        ++answer
        board[row][col] = -1
        for (k in dx.indices) {
            val (nrow, ncol) = listOf(row + dx[k], col + dy[k])
            if (board.indices.contains(nrow) && board[0].indices.contains(ncol) && board[nrow][ncol] != -1) {
                ++board[nrow][ncol]
                if (board[nrow][ncol] >= 10) {
                    flash(nrow, ncol)
                }
            }
        }
    }
    while (true) {
        val row = reader.readLine()
        if (row.isNullOrEmpty()) break
        board.add(row.map { it.digitToInt() }.toMutableList())
    }
    for (t in 0 until 100) {
        for (rowIndex in board.indices) {
            for (colIndex in board[0].indices) {
                ++board[rowIndex][colIndex]
            }
        }
        for (rowIndex in board.indices) {
            for (colIndex in board[0].indices) {
                if (board[rowIndex][colIndex] == 10) {
                    flash(rowIndex, colIndex)
                }
            }
        }
        for (rowIndex in board.indices) {
            for (colIndex in board[0].indices) {
                if (board[rowIndex][colIndex] == -1) {
                    board[rowIndex][colIndex] = 0
                }
            }
        }
    }
    println(answer)
}