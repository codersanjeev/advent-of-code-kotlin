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

data class Cell(val value: Int, val marked: Boolean = false)

data class Board(val rows: MutableList<MutableList<Cell>>) {
    fun mark(number: Int) {
        for (row in rows) {
            row.replaceAll { cell ->
                if (cell.value == number) {
                    cell.copy(cell.value, true)
                } else {
                    cell
                }
            }
        }
    }

    fun unmarkedCellsSum(): Int {
        return rows.flatten().filter {
            !it.marked
        }.sumOf {
            it.value
        }
    }

    fun isWinning(): Boolean {
        if (rows.any { row ->
                row.all { it.marked }
            }) {
            return true
        }
        for (columnIndex in rows[0].indices) {
            var goodColumn = true
            for (rowIndex in rows.indices) {
                if (!rows[rowIndex][columnIndex].marked) {
                    goodColumn = false
                    break
                }
            }
            if (goodColumn) return true
        }
        return false
    }

    companion object {
        fun initBoard(board: List<List<Int>>): Board {
            return Board(
                board.map { row ->
                    row.map { Cell(it) }.toMutableList()
                }.toMutableList()
            )
        }
    }
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
