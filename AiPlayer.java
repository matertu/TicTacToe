import java.util.Random;

public class AiPlayer extends Player {
    private Random random = new Random();

    public AiPlayer(char symbol) {
        super(symbol);
    }

    @Override
    public int setIndex()
    {
        int index = random.nextInt(9);
        return (index);
    }
}