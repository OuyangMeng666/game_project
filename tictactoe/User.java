package tictactoe;


import java.util.Scanner;

public class User implements Player {
    private final Scanner sc = new Scanner(System.in);
    private final Game game;
    private final char symbol;

    public User(Game game, char symbol) {
        this.game = game;
        this.symbol = symbol;
    }

    @Override
    public void makeTurn() {
        while(true) {
            System.out.print("Enter the coordinates: ");
            String[] input = sc.nextLine().split(" ");
            if (input.length != 2) {
                System.out.println("Bad coordinates, you should enter two numbers");
                continue;
            }
            int row, col;
            try {
                row =  Integer.parseInt(input[0]);
                col = Integer.parseInt(input[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            int pos = (row - 1) * 3 + (col - 1);
            if (game.isSet(pos)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            game.changeBoard(pos, symbol);
            return;
        }
    }
}