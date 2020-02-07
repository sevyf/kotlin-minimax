package board.tictactoe

import ai.MiniMaxAi
import board.Board
import board.GameState
import board.SpaceState
import player.Player
import java.util.*
import kotlin.collections.ArrayList

class TicTacToeBoard : Board {
    val board: Array<SpaceState>

    constructor() {
        board = arrayOf(
            SpaceState.EMPTY, SpaceState.EMPTY, SpaceState.EMPTY,
            SpaceState.EMPTY, SpaceState.EMPTY, SpaceState.EMPTY,
            SpaceState.EMPTY, SpaceState.EMPTY, SpaceState.EMPTY
        )
    }

    constructor(incoming: TicTacToeBoard) {
        board = incoming.board.copyOf()
    }

    override fun getCopy(): Board {
        return TicTacToeBoard(this)
    }

    companion object {
        val pieceMap = hashMapOf<String,Int>(
            "A" to 0, "B" to 1, "C" to 2,
            "D" to 3, "E" to 4, "F" to 5,
            "G" to 6, "H" to 7, "I" to 8
        )
    }

    fun p(letter:String): SpaceState {
        return board[pieceMap[letter]!!]
    }

    override fun getState(): GameState {
        val middleFilled = board[4] != SpaceState.EMPTY
        if (middleFilled) {
            if (
                (board[0] == board[4] && board[4] == board[8])
                || (board[6] == board[4] && board[4] == board[2])
            ) {
                return gameStateDetermineWinner(board[4])
            }
        }
        //vertical
        if (board[0] == board[3] && board[3] == board[6] && board[6] != SpaceState.EMPTY) {return gameStateDetermineWinner(board[0])}
        if (board[1] == board[4] && board[4] == board[7] && board[7] != SpaceState.EMPTY) {return gameStateDetermineWinner(board[1])}
        if (board[2] == board[5] && board[5] == board[8] && board[8] != SpaceState.EMPTY) {return gameStateDetermineWinner(board[2])}

        //horizontal
        if (board[0] == board[1] && board[1] == board[2] && board[2] != SpaceState.EMPTY) {return gameStateDetermineWinner(board[0])}
        if (board[3] == board[4] && board[4] == board[5] && board[5] != SpaceState.EMPTY) {return gameStateDetermineWinner(board[3])}
        if (board[6] == board[7] && board[7] == board[8] && board[8] != SpaceState.EMPTY) {return gameStateDetermineWinner(board[6])}

        board.iterator().forEach {
            if (it == SpaceState.EMPTY) {
                return GameState.ONGOING
            }
        }

        return GameState.DRAW
    }

    override fun printBoard() {
        println("-------------")
        println("|${board[0].printableState}|${board[1].printableState}|${board[2].printableState}|")
        println("|${board[3].printableState}|${board[4].printableState}|${board[5].printableState}|")
        println("|${board[6].printableState}|${board[7].printableState}|${board[8].printableState}|")
        println("-------------")
    }

    fun getPiece(player:Player): SpaceState {
        val piece: SpaceState
        if (player.type == GameState.OWINS) {
            piece = SpaceState.O
        } else {
            piece = SpaceState.X
        }
        return piece
    }

    override fun promptForMove(player: Player) {
        if (player.isComputer) {
            val nextMove = MiniMaxAi(this, player.type, 999999999).findMove()
            if (nextMove == null) {
                println("Error stat")
            } else {
                makeMove(getPiece(player), nextMove)
            }
        } else {
            var valid = false
            while (!valid) {
                println("${player.name} make your move x then y")
                val reader = Scanner(System.`in`)
                val line = reader.nextLine()
                valid = makeMove(player, line)
            }
        }
        printBoard()
    }

    fun gameStateDetermineWinner(spaceState: SpaceState): GameState {
        if (spaceState == SpaceState.X) {
            return GameState.XWINS;
        }
        if (spaceState == SpaceState.O) {
            return GameState.OWINS;
        }
        return GameState.IMPOSSIBLE
    }

    override fun makeMove(player: Player, move:String):Boolean {
        if (move.toLowerCase() == "help") {
            printHelp()
            return false
        }

        val theMove = pieceMap[move]
        if (theMove == null) {
            println("Invalid move")
            return false
        }
        val spaceState = getPiece(player)
        return makeMove(spaceState, theMove)
    }

    override fun makeMove(piece: SpaceState, space:Int):Boolean {
        if (board.size <= space || space < 0) {
            println("Invalid move")
            return false
        }
        if (board[space] == SpaceState.EMPTY) {
            board[space] = piece
            return true
        } else {
            println("Cannot place marker on a non empty space!")
            return false
        }
    }

    override fun getOpenSpaces(): ArrayList<Int> {
        val spaces:ArrayList<Int> = arrayListOf()
        board.forEachIndexed {index, ticTacToeSpaceState ->
            if (ticTacToeSpaceState == SpaceState.EMPTY) {
                spaces.add(index)
            }
        }
        return spaces
    }

    fun whosTurn(): SpaceState {
        var countX = 0
        var countO = 0
        board.iterator().forEach {
            if (it == SpaceState.X) {
                countX++
            } else {
                countO++
            }
        }
        if (countO > countX) {
            return SpaceState.X
        }
        return SpaceState.O
    }

    override fun printHelp() {
        println("-------------")
        println("| A | B | C |")
        println("| D | E | F |")
        println("| G | H | I |")
        println("-------------")
    }
}