package _KurbanovRauf

import java.util.*

data class Desk(val boardRepr: String) {
    val cells: List<String> = boardRepr.lines().map { it.filterIndexed { i, c -> i % 2 == 0 } }

    fun startPos(): List<Pair<Int, Int>> {
        val x: Int = cells.indexOfFirst { it.contains('S') }
        val y: Int = cells[x].indexOf('S')
        return listOf(Pair(x, y))
    }

    fun isOnBoard(pos: Pair<Int, Int>) =
            pos.first in 0..cells.size - 1 && pos.second in 0..cells[pos.first].length - 1

    fun getCellValue(cell: Pair<Int, Int>, currPos: List<Pair<Int, Int>>): Char? {
        if (currPos.contains(cell))
            return 'x'
        if (!isOnBoard(cell) || cells[cell.first][cell.second] == ' ')
            return null
        return cells[cell.first][cell.second]
    }

    fun onFinish(pos: List<Pair<Int, Int>>) =
            pos.map { cells[it.first][it.second] }.contains('T')

    fun printWithBlock(blockPos: List<Pair<Int, Int>>): String {
        val newCells: MutableList<String> = ArrayList(cells)
        for (pos in blockPos) {
            val str = newCells[pos.first]
            newCells[pos.first] = str.substring(0, pos.second) + 'x' + str.substring(pos.second + 1)
        }
        val spaced = newCells.map { it.replace("", " ") }
        return spaced.map { it.substring(1, it.length - 1) }.joinToString("\n")
    }
}
