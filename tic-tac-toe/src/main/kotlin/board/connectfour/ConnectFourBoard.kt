package board.connectfour

import board.Board
import board.GameState
import board.SpaceState
import player.Player

class ConnectFourBoard : Board {
    val board: Array<Array<SpaceState>> = Array(6) { Array(7){ SpaceState.EMPTY} }

    override fun printBoard() {
        println("-----------------------------")
        board.forEach {spaces ->
            print("|")
            spaces.forEach {
                print(it.printableState + "|")
            }
            println()
        }
        println("-----------------------------")
    }

    override fun promptForMove(player: Player) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun printHelp() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getState(): GameState {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun makeMove(player: Player, move: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun makeMove(piece: SpaceState, space: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOpenSpaces(): ArrayList<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCopy(): Board {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}