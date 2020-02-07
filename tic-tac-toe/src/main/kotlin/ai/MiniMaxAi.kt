package ai

import board.Board
import board.GameState
import board.SpaceState
import board.tictactoe.TicTacToeBoard

// it is also the desired winners turn
class MiniMaxAi(val board: Board, val desiredWinner: GameState, val difficulty:Int) {
    val me: GameState
    val mePiece: SpaceState
    val them: GameState
    val themPiece: SpaceState

    init {
        me = desiredWinner
        if (desiredWinner == GameState.XWINS) {
            mePiece = SpaceState.X
            them = GameState.OWINS
            themPiece = SpaceState.O
        } else {
            mePiece = SpaceState.O
            them = GameState.XWINS
            themPiece = SpaceState.X
        }
    }

    fun findMove():Int?{
        val possibleMoves = board.getOpenSpaces()
        val results = arrayListOf<Int>()

        // start off maximizing
        for (value in possibleMoves) {
            val childBoard = board.getCopy()
            childBoard.makeMove(mePiece, value)
            results.add(minimax(childBoard, difficulty, them))
        }

        for ((index, value) in results.withIndex()) {
            if (value == 1) {
                return possibleMoves[index]
            }
        }

        for ((index, value) in results.withIndex()) {
            if (value == 0) {
                return possibleMoves[index]
            }
        }

        for ((index, value) in results.withIndex()) {
            if (value == -1) {
                return possibleMoves[index]
            }
        }

        return null
    }

    fun minimax(node: Board, depth:Int, player: GameState):Int {
        val stateOfNode = node.getState()
        if (depth == 0
            || stateOfNode == GameState.XWINS
            || stateOfNode == GameState.OWINS
            || stateOfNode == GameState.DRAW) {
            if (stateOfNode == me) {
                return 1
            } else if (stateOfNode == GameState.DRAW) {
                return 0
            }
            return -1
        }

        val nextLevel = depth -1
        var decision:Int
        val availableMoves = node.getOpenSpaces()
        //maximizing
        if (player == me) {
            decision = -1
            for (space in availableMoves) {
                val childBoard = node.getCopy()
                childBoard.makeMove(mePiece, space)
                decision = maxOf(decision, minimax(childBoard, nextLevel, them))
            }
        } else {
            decision = 1
            //minimizing
            for (space in availableMoves) {
                val childBoard = node.getCopy()
                childBoard.makeMove(themPiece, space)
                decision = minOf(decision, minimax(childBoard, nextLevel, me))
            }
        }
        return decision
    }
}