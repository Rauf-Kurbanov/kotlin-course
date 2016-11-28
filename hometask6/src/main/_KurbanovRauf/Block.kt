package _KurbanovRauf

import bloxorz.Direction

enum class BlockState {
    STANDING,
    VERTICAL,
    HORIZONTAL
}

class Block(val startPos: List<Pair<Int, Int>>, val posChecker: (List<Pair<Int, Int>>) -> Boolean) {

    var blockState: BlockState = BlockState.STANDING

    var currPos = startPos
        get() = field
        private set(value) {
            if (posChecker(value)) {
                field = value
                return
            }
            field = startPos
            blockState = BlockState.STANDING
        }

    fun Pair<Int, Int>.neighbour(direction: Direction) = when (direction) {

        Direction.UP -> Pair(first - 1, second)
        Direction.DOWN -> Pair(first + 1, second)
        Direction.LEFT -> Pair(first, second - 1)
        Direction.RIGHT -> Pair(first, second + 1)
    }

    fun move(direction: Direction) {
        when (blockState) {
            BlockState.STANDING -> {
                val onePos = currPos.first()
                blockState = when (direction) {
                    Direction.UP, Direction.DOWN -> BlockState.VERTICAL
                    Direction.LEFT, Direction.RIGHT -> BlockState.HORIZONTAL
                }
                currPos = listOf(onePos.neighbour(direction)
                        , onePos.neighbour(direction).neighbour(direction))
            }
            BlockState.VERTICAL -> currPos = when (direction) {

                Direction.UP -> {
                    blockState = BlockState.STANDING
                    listOf(currPos.minBy { it.first }?.neighbour(direction)
                            ?: throw InvalidCellPositionException())
                }
                Direction.DOWN -> {
                    blockState = BlockState.STANDING
                    listOf(currPos.maxBy { it.first }?.neighbour(direction)
                            ?: throw InvalidCellPositionException())

                }
                else -> currPos.map { it.neighbour(direction) }
            }
            BlockState.HORIZONTAL -> currPos = when (direction) {

                Direction.RIGHT -> {
                    blockState = BlockState.STANDING
                    listOf(currPos.maxBy { it.second }?.neighbour(direction)
                            ?: throw InvalidCellPositionException())
                }
                Direction.LEFT -> {
                    blockState = BlockState.STANDING
                    listOf(currPos.minBy { it.second }?.neighbour(direction)
                            ?: throw InvalidCellPositionException())
                }
                else -> currPos.map { it.neighbour(direction) }
            }
        }
    }
}
