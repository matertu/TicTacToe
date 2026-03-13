public abstract class Player
{
    protected char symbol;

    public Player(char c)
    {
        if(c == 'X' || c == 'O')
            this.symbol = c;
    }

    public char getSymbol()
    {
        return (this.symbol);
    }

    public abstract int setIndex();
}