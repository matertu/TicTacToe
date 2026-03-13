import java.util.Scanner;

public class RunGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        
        Player player1 = null;
        Player player2 = null;
        int rounds = 0;
        int nPlayer;
        char c = ' ';

        System.out.println(Utils.green("\n>>>>>>>> TicTacToe Game <<<<<<<<\n"));

        do {
            System.out.print(" Choice 1 or 2 players: ");
            nPlayer = sc.nextInt();
        } while(nPlayer != 1 && nPlayer != 2);
        
        do {
            System.out.print(" Choice X or O: ");
            String input = sc.next().toUpperCase();

            if (input.length() > 0) {
                c = input.charAt(0);
                if (c == 'X' || c == 'O') {
                    player1 = new HumanPlayer(c);
                }
            }
        } while (c != 'X' && c != 'O');

        char s2 = (player1.getSymbol() == 'X' ? 'O' : 'X');
        if (nPlayer == 2) {
            player2 = new HumanPlayer(s2);
        } else {
            player2 = new AiPlayer(s2);
        }

        do {
            System.out.print(" How many rounds (1-5): ");
            rounds = sc.nextInt();
        } while (rounds < 1 || rounds > 5);

        int currentRound = 1;
        while (currentRound <= rounds) {
            board.reset();
            boolean gameOver = false;
            Player currentPlayer = (player1.getSymbol() == 'X') ? player1 : player2;

            do {
                Utils.clearScreen();
                System.out.println(Utils.green("\n\n==== ROUND " + currentRound + " ====\n"));
                board.print();
                System.out.println(" Player " + currentPlayer.getSymbol() + " turn.");
                
                int index = currentPlayer.setIndex();

                if (board.makeMove(index, currentPlayer.getSymbol())) {
                    char winner = board.checkWinner();
                    if (winner != '\0') {
                        Utils.clearScreen();
                        board.print();
                        System.out.println(Utils.green(" Player " + winner + " WON THIS ROUND!"));
                        gameOver = true;
                    } else if (board.isFull()) {
                        Utils.clearScreen();
                        board.print();
                        System.out.println(" DRAW!");
                        gameOver = true;
                    } else {
                        currentPlayer = (currentPlayer == player1) ? player2 : player1;
                    }
                } else {
                    if(currentPlayer instanceof HumanPlayer)
                    {
                        System.out.println(Utils.red(" Invalid position! Try again.\n"));
                        try { Thread.sleep(800); } catch (Exception e) {}
                    }
                }
            } while (!gameOver);
            
            currentRound++;
        }
        sc.close();
    }
}