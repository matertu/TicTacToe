public final class Utils {
    private Utils() {}

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";

    public static int[] transformIndex(int num) {
        int n = num - 1;

        int row = n / 3;
        int column = n % 3;

        return new int[] {row, column};
    }

    public static String red(String message)
    {
        return RED + message + RESET;
    }

    public static String green(String message)
    {
        return GREEN + message + RESET;
    }

    public static String blue(String message)
    {
        return BLUE + message + RESET;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}