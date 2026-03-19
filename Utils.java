public final class Utils {
    private Utils() {}

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";

    public static int[] positionToIndex(int num, int size) {
        int n = num - 1;

        int row = n / size;
        int column = n % size;

        return new int[] {row, column};
    }

    public static int indexToPosition(int i, int j, int size) {
        return ((i * size) + j + 1);
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