package _KurbanovRauf

import bloxorz.Direction
import bloxorz.Direction.*
import bloxorz.Game
import bridges.BridgesInfo

// Your solution should live in this folder/package only (rename _SurnameName accordingly.)
// You may add as many subpackages as you need, but the function 'createGame' below should live in the root _SurnameName package.
// Please DON'T copy the interface 'Game' here.

fun createGame(board: String, bridgesInfo: BridgesInfo? = null): Game = GameImpl(board)

