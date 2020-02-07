import board.Game
import board.GameState
import board.connectfour.ConnectFourBoard
import board.tictactoe.TicTacToeBoard
import player.Player
import java.util.*

fun main() {
    val player0 = Player(GameState.OWINS, "Sevy", false)
    val player1 = Player(GameState.XWINS, "Justin", true)

    while (true) {
        val reader = Scanner(System.`in`)
        println("Chose your game.")
        println("1. Tic Tac Toe")
        println("2. Connect Four")
        println("Q quit")
        val gameLine = reader.nextLine()

        if (gameLine.equals("Q")) {
            break
        }

        val game:Game
        when (gameLine) {
            "1" -> game = Game(player0, player1, TicTacToeBoard())
            "2" -> game = Game(player0, player1, ConnectFourBoard())
            else -> {
                game = Game(player0, player1, TicTacToeBoard())
            }
        }
        game.playGame()
    }
}