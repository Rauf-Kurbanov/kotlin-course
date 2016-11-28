package _KurbanovRauf

import bloxorz.Direction
import bloxorz.Game

class GameImpl(board: String) : Game {

    override val height = board.lineSequence().count()
    override val width = ((board.lineSequence().map(String::length).max() ?: 0) + 1) / 2
    val desk = Desk(board)
    val block = Block(desk.startPos()
            , { pos -> pos.all { desk.isOnBoard(it) && getCellValue(it.first + 1, it.second + 1) != null } })

    override fun hasWon(): Boolean = block.blockState == BlockState.STANDING && desk.onFinish(block.currPos)

    override fun getCellValue(i: Int, j: Int): Char? =
        desk.getCellValue(Pair(i - 1, j - 1), block.currPos)

    override fun processMove(direction: Direction) = block.move(direction)

    override fun toString(): String = desk.printWithBlock(block.currPos)

    override fun suggestMoves(): List<Direction>? {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}



