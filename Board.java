public class Board
{
    public char[][] board = new char[3][3];

    public Board()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
                board[i][j] = ' ';
        }
    }

    public void reset()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
                board[i][j] = ' ';
        }
    }

    public void print()
    {
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("  ");
            for (int j = 0; j < 3; j++)
            {
                char c =  board[i][j];
                if(c == 'X')
                    System.out.print(Utils.red(" X "));
                else if(c == 'O')
                    System.out.print(Utils.blue(" O "));
                else
                    System.out.print(Utils.blue("   "));


                if (j < 2) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (i < 2) {
                System.out.println("  ---+---+---");
            }
        }
        System.out.println();
    }

    public boolean makeMove(int num, char symbol)
    {
        int index[] = Utils.transformIndex(num);

        int row = index[0];
        int column = index[1];

        if(row > 2 || row < 0 || column > 2 || column < 0)
            return (false);

        else if(this.board[row][column] == ' ')
        {
            this.board[row][column] = symbol;
            return (true);
        }

        return (false);
    }

    public boolean isFull()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return (false);
        }
        return (true);
    }

    public char checkWinner()
    {
        char isWinner;

        isWinner = verifyColumn();
        if(isWinner != '\0')
            return (isWinner);

        isWinner = verifyRow();
        if(isWinner != '\0')
            return (isWinner);

        isWinner = verifyDiagonal();
        if(isWinner != '\0')
            return (isWinner);

        return ('\0');
    }

    private char verifyRow()
    {
        for(int i = 0; i < 3; i++)
        {
            if(board[i][0] != ' ' && board[i][1] == board[i][0] && board[i][2] == board[i][0])
                return (board[i][0]);
        }
        return ('\0');
    }

    private char verifyColumn()
    {
        for(int i = 0; i < 3; i++)
        {
            if(board[0][i] != ' ' && board[1][i] == board[0][i] && board[2][i] == board[0][i])
                return (board[0][i]);
        }
        return ('\0');
    }

    private char verifyDiagonal()
    {
        if(board[0][0] != ' ' && board[1][1] == board[0][0] && board[2][2] == board[1][1])
            return (board[0][0]);
        else if(board[0][2] != ' ' && board[1][1] == board[0][2] && board[2][0] == board[1][1])
            return (board[0][2]);
        return ('\0');
    }
}

