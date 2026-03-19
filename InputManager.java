import java.util.Scanner;

public class InputManager {
    private static Scanner sc = new Scanner(System.in);

    public static int getValidInt(int min, int max)
    {
        int value;
        do {
            while (!sc.hasNextInt())
                sc.next();

            value = sc.nextInt();
        } while(!(value >= min && value <= max));

        return (value);
    }

    public static char getValidSymbol()
    {
        char symbol = ' ';
        do {
            String input = sc.next().toUpperCase();
            if (input.length() > 0)
                symbol = input.charAt(0);

        } while (symbol != 'X' && symbol != 'O');
        
        return (symbol);
    }

    public static int getValidBoard(int max)
    {
        int value;
        do {
            while (!sc.hasNextInt())
                sc.next();

            value = sc.nextInt();
        } while(value != 9 && value != 16 && value != 25);

        return (value);
    }

    public static int getValidPosition(int maxPosition) {
        int position = 0;
        while (true) {
            try {
                System.out.print(" Enter position (1-" + maxPosition + "): ");
                position = sc.nextInt();

                if (position >= 1 && position <= maxPosition) {
                    return position;
                }
            } catch (java.util.InputMismatchException e) {
                sc.next();
            }
        }
    }
}