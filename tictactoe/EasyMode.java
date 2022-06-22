package tictactoe;

import java.util.Random;

public class EasyMode implements Player {
    static Random rand = new Random();
    private final Game game;
    private final char move;

    public EasyMode(Game game, char move) {
        this.game = game;
        this.move = move;
    }

    @Override
    public void makeTurn() {
        int posNum;
        do {
            posNum = rand.nextInt(3) * 3 + rand.nextInt(3);
        } while (game.isSet(posNum));
        game.changeBoard(posNum, move);
        System.out.println("Making move level \"easy\"");
    }
}