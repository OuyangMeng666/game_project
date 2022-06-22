package tictactoe;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        Player[] players = new Player[2];
        outer: while (true) {
            selection: while (true) {
                System.out.print("Input command: ");
                String[] input = sc.nextLine().split(" ");
                if (input[0].equals("start")) {
                    if (input.length < 3) {
                        System.out.println("Bad parameters!");
                        continue;
                    }
                    for (int i = 1; i < 3; i++) {
                        switch (input[i]) {
                            case "user":
                                players[i - 1] = new User(game, i == 1 ? 'X' : 'O');
                                break;
                            case "easy":
                                players[i - 1] = new EasyMode(game, i == 1 ? 'X' : 'O');
                                break;
                            case "medium":
                                players[i - 1] = new MediumMode(game, i == 1 ? 'X' : 'O');
                                break;
                            default:
                                System.out.println("Bad parameters!");
                                continue selection;
                        }
                    }
                } else {
                    if (input[0].equals("exit") || input[0].equals("stop")) {
                        break outer;
                    }
                    System.out.println("Please input correct command or type 'exit'");
                    continue;
                }
                break;
            }

            game.reloadBoard();
            game.printBoard();
            boolean XNext = true;

            do {
                players[XNext ? 0 : 1].makeTurn();
                XNext = !XNext;
                game.printBoard();
            } while (!game.hasWinner() && !game.hasBoardFull());

            if (game.hasWinner()) {
                System.out.printf("%c wins%n%n", game.checkWhoWon());
            } else {
                System.out.println("Draw\n");
            }
        }
    }
}