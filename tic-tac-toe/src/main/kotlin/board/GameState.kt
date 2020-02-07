package board

enum class GameState(val printableState: String, val person: SpaceState) {
    ONGOING("ongoing", SpaceState.EMPTY),
    XWINS("X wins", SpaceState.X),
    OWINS("O Wins", SpaceState.O),
    DRAW("Tis a draw", SpaceState.EMPTY),
    IMPOSSIBLE("nah dawg", SpaceState.EMPTY)
}