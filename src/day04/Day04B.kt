package day04

import getFileReader
import java.io.BufferedReader

private fun readBoards(reader: BufferedReader): MutableList<Board> {
    val boards = mutableListOf<Board>()
    val currentBoard = mutableListOf<MutableList<Int>>()
    reader.lines().forEach {
        if (currentBoard.size == 5) {
            boards.add(Board.initBoard(currentBoard))
            currentBoard.clear()
        } else {
            currentBoard.add(
                it.split("\\s+".toRegex())
                    .mapNotNull { it.toIntOrNull() }
                    .toMutableList()
            )
        }
    }
    boards.add(Board.initBoard(currentBoard))
    return boards
}

fun main() {
    val reader = getFileReader("src/day04/input.txt")
    val numbers = reader.readLine().split(",").map { it.toInt() }
    reader.readLine()
    var boards = readBoards(reader)
    for (number in numbers) {
        for (board in boards) {
            board.mark(number)
            if (board.isWinning()) {
                val unmarkedSum = board.unmarkedCellsSum()
                println(unmarkedSum * number)
                boards = (boards - board).toMutableList()
            }
        }
    }
}
