package board

import board.tictactoe.TicTacToeBoard
import player.Player

class Game(val player0: Player, val player1: Player, val board: Board) {
    var lastTurn = player1 //set the last to the second player so player 0 always goes first

    fun playGame() {
        board.printBoard()
        while (!gameOver()) {
            playTurn()
        }
    }

    fun playTurn() {
        val player = if (lastTurn == player1) player0 else player1
        lastTurn = player
        board.promptForMove(player)
    }

    fun gameOver():Boolean{
        val gameState = board.getState();
        if (gameState == GameState.ONGOING) {
            return false;
        }
        println(gameState.printableState)
        return true;
    }
}