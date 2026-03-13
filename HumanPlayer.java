import java.util.Scanner;
import java.util.InputMismatchException;

public class HumanPlayer extends Player {
    private Scanner sc = new Scanner(System.in);

    public HumanPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public int setIndex() {
        int p = 0;
        while (true) {
            try {
                System.out.print(" Enter position (1-9): ");
                p = sc.nextInt();

                if (p >= 1 && p <= 9) {
                    return p;
                }
            }
            catch (InputMismatchException e)
            {
                sc.next();
            }
        }
    }

    
}