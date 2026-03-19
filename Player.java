public abstract class Player {
    protected char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return (this.symbol);
    }

    public abstract int setMove(Board board);
}