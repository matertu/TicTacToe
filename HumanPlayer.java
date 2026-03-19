public class HumanPlayer extends Player {
    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public int setMove(Board board) {
        return InputManager.getValidPosition(board.getMaxPosition());
    }
}