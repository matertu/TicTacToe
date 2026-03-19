public class Board {
    public int size;
    private char[][] board;

    public Board(int n) {
        this.size = n;
        this.board = new char[this.size][this.size];
        clear();
    }

    public int getSize() {
        return (this.size);
    }

    public int getMaxPosition() {
        return (this.size * this.size);
    }

    public void clear() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    public char getSymbol(int i, int j) {
        return (board[i][j]);
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.printf("  ");

            for (int j = 0; j < this.size; j++) {
                char c = board[i][j];

                if (c == 'X') {
                    System.out.print(Utils.red(" X "));
                } else if (c == 'O') {
                    System.out.print(Utils.blue(" O "));
                } else {
                    System.out.print("   ");
                }

                if (j < this.size - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();
            System.out.printf("  ");

            if (i < this.size - 1) {
                for (int k = 1; k <= (this.size * 4 - 1); k++) {
                    if (k % 4 == 0) {
                        System.out.print("+");
                    } else {
                        System.out.print("-");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public boolean makeMove(int num, char symbol) {
        int[] index = Utils.positionToIndex(num, this.size);

        if (this.board[index[0]][index[1]] == ' ') {
            this.board[index[0]][index[1]] = symbol;
            return (true);
        }
        return (false);
    }

    public void undoMove(int num) {
        int[] index = Utils.positionToIndex(num, this.size);

        if (this.board[index[0]][index[1]] != ' ') {
            this.board[index[0]][index[1]] = ' ';
        }
    }

    public boolean isFull() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (board[i][j] == ' ') {
                    return (false);
                }
            }
        }
        return (true);
    }

    public char checkWinner() {
        char isWinner;

        isWinner = verifyColumn();
        if (isWinner != '\0') {
            return (isWinner);
        }

        isWinner = verifyRow();
        if (isWinner != '\0') {
            return (isWinner);
        }

        isWinner = verifyPrimaryDiagonal();
        if (isWinner != '\0') {
            return (isWinner);
        }

        isWinner = verifySecondaryDiagonal();
        if (isWinner != '\0') {
            return (isWinner);
        }

        return ('\0');
    }

    private char verifyColumn() {
        for (int j = 0; j < this.size; j++) {
            char ref = board[0][j];
            if (ref == ' ') {
                continue;
            }
            
            int cont = 0;
            for (int i = 0; i < this.size; i++) { 
                if (board[i][j] == ref) {
                    cont++;
                } else {
                    break;
                }
            }
            if (cont == this.size) {
                return (ref);
            }
        }
        return ('\0');
    }

    private char verifyRow() {
        for (int i = 0; i < this.size; i++) {
            char ref = board[i][0];
            if (ref == ' ') {
                continue;
            }
            
            int cont = 0;
            for (int j = 0; j < this.size; j++) { 
                if (board[i][j] == ref) {
                    cont++;
                } else {
                    break;
                }
            }
            if (cont == this.size) {
                return (ref);
            }
        }
        return ('\0');
    }

    private char verifyPrimaryDiagonal() {
        char ref = board[0][0];
        if (ref == ' ') {
            return ('\0');
        }

        for (int i = 0; i < this.size; i++) {
            if (board[i][i] != ref) {
                return ('\0');
            }
        }
        return (ref);
    }

    private char verifySecondaryDiagonal() {
        char ref = board[0][this.size - 1];
        if (ref == ' ') {
            return ('\0');
        }

        for (int i = 0; i < this.size; i++) {
            if (board[i][this.size - 1 - i] != ref) {
                return ('\0');
            }
        }
        return (ref);
    }
}