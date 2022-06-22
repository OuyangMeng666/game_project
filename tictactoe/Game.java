package tictactoe;

import java.util.Arrays;

public class Game {
    private int smartMovePos;
    private final char[] gameboard = new char[9];

    public void reloadBoard() {
        Arrays.fill(gameboard, ' ');
    }

    public void printBoard() {
        System.out.println("---------");
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %c %c %c |%n", gameboard[counter++], gameboard[counter++], gameboard[counter++]);
        }
        System.out.println("---------");
    }

    public boolean isSet(int posNum) {
        return gameboard[posNum] != ' ';
    }

    public void changeBoard(int pos, char symbol) {
        gameboard[pos] = symbol;
    }

    public char checkWhoWon() {
        for (int i = 0; i < 3; i++) {
            // check horizontal
            if (gameboard[3 * i] == gameboard[3 * i + 1] && gameboard[3 * i] == gameboard[3 * i + 2]) {
                return gameboard[3 * i];
            }
            // check vertical
            if (gameboard[i] == gameboard[i + 3] && gameboard[i] == gameboard[i + 6]) {
                return gameboard[i];
            }
        }
        // check diagonal
        if (gameboard[0] == gameboard[4] && gameboard[0] == gameboard[8] || gameboard[2] == gameboard[4] && gameboard[2] == gameboard[6]) {
            return gameboard[4];
        }
        return ' ';
    }

    public char checkPossibleWin() {
        for (int i = 0; i < 3; i++) {
            // check horizontal
            if (checkPossibleWinRow(3 * i, 3 * i + 1, 3 * i + 2) != ' ') {
                return checkPossibleWinRow(3 * i, 3 * i + 1, 3 * i + 2);
            }
            // check vertical
            if (checkPossibleWinRow(i, i + 3, i + 6) != ' ') {
                return checkPossibleWinRow(i, i + 3, i + 6);
            }
        }
        // check diagonal
        if (checkPossibleWinRow(0 , 4, 8) != ' ') {
            return checkPossibleWinRow(0 , 4, 8);
        }
        if (checkPossibleWinRow(2 , 4, 6) != ' ') {
            return checkPossibleWinRow(2 , 4, 6);
        }
        return ' ';
    }

    public char checkPossibleWinRow(int first, int second, int third) {
        int num_x = 0, num_o = 0, num_empty = 0;
        for (char c : new char[]{gameboard[first], gameboard[second], gameboard[third]}) {
            if (c == 'X') {
                num_x++;
            } else if (c == 'O') {
                num_o++;
            } else if (c == ' ') {
                num_empty++;
            }
        }
        if (num_empty == 1) {
            if (num_x == 2) {
                smartMovePos = gameboard[first] == ' ' ? first : (gameboard[second] == ' ' ? second : third);
                return 'X';
            } else if (num_o == 2) {
                smartMovePos = gameboard[first] == ' ' ? first : (gameboard[second] == ' ' ? second : third);
                return 'O';
            }
        }
        return ' ';
    }

    public void makeSmartMove(char symbol) {
        changeBoard(smartMovePos, symbol);
    }

    public boolean hasWinner() {
        return checkWhoWon() != ' ';
    }

    public boolean hasBoardFull() {
        for (char field : gameboard) {
            if (field == ' ') {
                return false;
            }
        }
        return true;
    }
}