import java.util.Random;

public class MachinePlayer extends Player {
    private Random random = new Random();
    private int skill = 0;
    private int[][] boardWeight;
    private int size;

    public MachinePlayer(char symbol, int skill) {
        super(symbol);
        this.skill = skill;
    }
    
    @Override
    public int setMove(Board board) {
        this.size = board.getSize();
        int maxPosition = board.getMaxPosition();

        if (this.skill == 1) {
            return (random.nextInt(maxPosition) + 1);
        } else if (this.skill == 2) {
            return executeSkill2(board);
        } else if (this.skill == 3) {
            int winMove = findWinningMove(board, this.symbol);
            if (winMove != -1) return winMove;

            char opponentSymbol = (this.symbol == 'X') ? 'O' : 'X';
            int blockMove = findWinningMove(board, opponentSymbol);
            if (blockMove != -1) return blockMove;

            return executeSkill2(board);
        }
        return (1);
    }

    private int executeSkill2(Board board) {
        if (this.boardWeight == null || isBoardEmpty(board)) {
            generateDynamicWeights();
        }
        return getBestPosition(board);
    }

    private int findWinningMove(Board board, char sym) {
        for (int i = 1; i <= board.getMaxPosition(); i++) {
            int[] idx = Utils.positionToIndex(i, size);
            
            if (board.getSymbol(idx[0], idx[1]) == ' ') {
                board.makeMove(i, sym);
                boolean isWin = (board.checkWinner() == sym);
                board.undoMove(i);
                
                if (isWin) return i;
            }
        }
        return -1;
    }

    private boolean isBoardEmpty(Board board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getSymbol(i, j) != ' ') return false;
            }
        }
        return true;
    }

    private void generateDynamicWeights() {
        this.boardWeight = new int[size][size];
        int center = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int distI = Math.abs(i - center);
                int distJ = Math.abs(j - center);
                int maxDist = Math.max(distI, distJ);
                int val = size - maxDist;
                this.boardWeight[i][j] = val * val;
            }
        }   
        int last = size - 1;
        this.boardWeight[0][0] += 2;
        this.boardWeight[0][last] += 2;
        this.boardWeight[last][0] += 2;
        this.boardWeight[last][last] += 2;
    }

    private int getBestPosition(Board board) {
        int position = 0;
        int attempts = 0;
        int maxPossibleAttempts = size * size;

        while (position == 0 && attempts < maxPossibleAttempts) {
            int[] index = getMaxWeight(); 
            if (index[0] == -1) {
                break;
            }
            int candidatePos = Utils.indexToPosition(index[0], index[1], this.size);
            if (board.getSymbol(index[0], index[1]) == ' ') {
                position = candidatePos;
            } else {
                this.boardWeight[index[0]][index[1]] = -1;
            }
            attempts++;
        }
        if (position == 0) {
            for (int i = 1; i <= board.getMaxPosition(); i++) {
                int[] idx = Utils.positionToIndex(i, size);
                if (board.getSymbol(idx[0], idx[1]) == ' ')
                    return i;
            }
        }
        return position;
    }

    private int[] getMaxWeight() {
        int[] indexMax = {-1, -1};
        int maxVal = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.boardWeight[i][j] > maxVal) {
                    maxVal = this.boardWeight[i][j];
                    indexMax[0] = i;
                    indexMax[1] = j;
                }
            }
        }
        return indexMax;
    }
}