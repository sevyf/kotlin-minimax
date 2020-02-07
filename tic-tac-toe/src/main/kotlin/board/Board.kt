package board

import player.Player

interface Board {
    fun printBoard()
    fun promptForMove(player:Player)
    fun printHelp()
    fun getState(): GameState
    fun makeMove(player: Player, move:String):Boolean
    fun getOpenSpaces():ArrayList<Int>
    fun getCopy():Board
    fun makeMove(piece: SpaceState, space:Int):Boolean
}