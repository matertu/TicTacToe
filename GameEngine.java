public class GameEngine {
    private Board board;
    private Player player1;
    private Player player2;
    private char currentPlayer = 'O';
    private int numRounds;
    private int boardSize;
    private int p1Points;
    private int p2Points;
    private char winnerSymbol = '\0';

    public GameEngine() {
        this.p1Points = 0;
        this.p2Points = 0;
    }

    public void setup() {
        System.out.println(Utils.green("\n>> TicTacToe Game <<\n"));
        setConfig();
        initPlayers();
    }

    public void playMatch() {
        int currentRound = 1;
        int winningPost = (this.numRounds / 2) + 1;

        while (this.p1Points < winningPost && this.p2Points < winningPost && currentRound <= this.numRounds) {
            winnerSymbol = playRound(currentRound);

            if (winnerSymbol != '\0') {
                if (player1.getSymbol() == winnerSymbol) {
                    this.p1Points++;
                } else if (player2.getSymbol() == winnerSymbol) {
                    this.p2Points++;
                }
            }
            currentRound++;
        }
        printResult();
        printScoreboard();
    }

    public char playRound(int currentRound) {
        board.clear();
        boolean endGame = false;
        int index;
        boolean play;
        
        this.currentPlayer = (this.currentPlayer == 'X' ? 'O' : 'X');
        char roundTurn = this.currentPlayer;

        while (!endGame) {
            Utils.clearScreen();
            Player activePlayer = (player1.getSymbol() == roundTurn) ? player1 : player2;

            printCurrentRound(currentRound);
            printPoints();
            board.print();

            do {
                index = activePlayer.setMove(board);
                play = board.makeMove(index, roundTurn);
            } while (!play);

            winnerSymbol = board.checkWinner();
            if (winnerSymbol != '\0' || board.isFull()) {
                endGame = true;
            } else {
                roundTurn = (roundTurn == 'X' ? 'O' : 'X');
            }
        }
        
        finalizeRoundDisplay(currentRound);
        return winnerSymbol;
    }

    private void finalizeRoundDisplay(int currentRound) {
        Utils.clearScreen();
        printCurrentRound(currentRound);
        printPoints();
        board.print();
    }

    private void setConfig() {
        System.out.print(" Choice board size (9-16-25): ");
        int totalSquares = InputManager.getValidBoard(25);
        this.boardSize = (int) Math.sqrt(totalSquares);
        this.board = new Board(this.boardSize);

        System.out.print(" How many rounds (1-5): ");
        this.numRounds = InputManager.getValidInt(1, 5);
    }

    private void initPlayers() {
        int inputPlayer;
        int inputSkill = 0;

        System.out.print(" Choice 1 or 2 players: ");
        inputPlayer = InputManager.getValidInt(1, 2);

        if (inputPlayer == 1) {
            System.out.print(" Chose bot skill (1-3): ");
            inputSkill = InputManager.getValidInt(1, 3);
        }

        System.out.print(" Chose symbol 'X' or 'O': ");
        player1 = new HumanPlayer(InputManager.getValidSymbol());

        if (inputPlayer == 1) {
            player2 = new MachinePlayer(player1.getSymbol() == 'X' ? 'O' : 'X', inputSkill);
        } else {
            player2 = new HumanPlayer(player1.getSymbol() == 'X' ? 'O' : 'X');
        }
    }

    private void printPoints() {
        System.out.println(" " + player1.getSymbol() + " points: " + this.p1Points);
        System.out.println(" " + player2.getSymbol() + " points: " + this.p2Points + "\n");
    }

    private void printCurrentRound(int currentRound) {
        System.out.println(Utils.green(" >>> Round: " + currentRound + " <<<\n"));
    }

    private void printResult() {
        char finalWinner = (p1Points > p2Points) ? player1.getSymbol() : 
                           (p2Points > p1Points) ? player2.getSymbol() : '\0';
        
        if (this.player2 instanceof MachinePlayer) {
            printMachineMatchResult(finalWinner);
        } else {
            printPVPResult(finalWinner);
        }
        System.out.println();
    }

    private void printMachineMatchResult(char finalWinner) {
        if (finalWinner == player1.getSymbol()) {
            System.out.println(Utils.green(" >>> You Won the Match! <<<"));
        } else if (finalWinner == player2.getSymbol()) {
            System.out.println(Utils.red(" >>> You Lose the Match! <<<"));
        } else {
            System.out.println(Utils.blue(" >>> Match Drawn! <<<"));
        }
    }

    private void printPVPResult(char finalWinner) {
        if (finalWinner != '\0') {
            System.out.println(Utils.green(" >>> Player " + finalWinner + " Won the Match! <<<"));
        } else {
            System.out.println(Utils.blue(" >>> Match Drawn! <<<"));
        }
    }

    private void printScoreboard() {
        System.out.println(" Player " + player1.getSymbol() + " Points: " + p1Points);
        System.out.println(" Player " + player2.getSymbol() + " Points: " + p2Points + "\n");
    }
}