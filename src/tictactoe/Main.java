package tictactoe;

import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    public static void printCells(char[] cells) {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.print("| " + cells[i] + " ");
            System.out.print(cells[i + 1] + " ");
            System.out.println(cells[i + 2] + " |");
        }
        System.out.println("---------");
    }
    public static void printStatus(char[] cells) {
        if (isImpossible(cells)) {
            System.out.println("Impossible");
            return;
        }
        if (isWin(cells, 'X')) {
            System.out.println("X wins");
            return;
        }
        if (isWin(cells, 'O')) {
            System.out.println("O wins");
            return;
        }
        if (getCountX(cells) + getCountO(cells) == 9) {
            System.out.println("Draw");
            return;
        }
        System.out.println("Game not finished");
    }
    public static boolean isWin(char[] cells, char player) {
        boolean isWin = false;
        for (int i = 0; i < 9; i += 3) {
            if (cells[i] == player && cells[i + 1] == player && cells[i + 2] == player) {
                isWin = true;
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (cells[i] == player && cells[i + 3] == player && cells[i + 6] == player) {
                isWin = true;
                break;
            }
        }
        if (cells[0] == player && cells[4] == player && cells[8] == player) {
            isWin = true;
        }
        if (cells[2] == player && cells[4] == player && cells[6] == player) {
            isWin = true;
        }
        return isWin;
    }
    public static boolean isEmptyCells(char[] cells) {
        boolean isEmptyCells = false;
        for (int i = 0; i < 9; i++) {
            if (cells[i] == ' ') {
                isEmptyCells = true;
            }
        }
        return isEmptyCells;
    }
    public static int getCountX(char[] cells) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (cells[i] != 'X') {
                count++;
            }
        }
        return count;
    }
    public static int getCountO(char[] cells) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (cells[i] != 'O') {
                count++;
            }
        }
        return count;
    }
    public static boolean isImpossible(char[] cells) {
        boolean isImpossible = false;
        if(isWin(cells, 'X') && isWin(cells, 'O')) {
            isImpossible = true;
        }
        if (abs(getCountO(cells) - getCountX(cells)) > 1) {
            isImpossible = true;
        }
        return isImpossible;
    }
    public static boolean isValidCoordinates (int x, int y) {
        boolean isValid = true;
        if (x > 3 || x < 1 || y > 3 || y < 1) {
            isValid = false;
        }
        return isValid;
    }
    public static boolean isOccupied (char[] cells, int x, int y) {
        boolean isOccupied = false;
        if (cells[y + (x - 1) * 3 - 1] != ' ') {
            isOccupied = true;
        }
        return isOccupied;
    }
    public static int getCoordinate (Scanner sc) {
        String str = sc.next();
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if (c < 48 || c > 57) {
                return 0;
            }
        }
        return Integer.parseInt(str);
    }
    public static void doStep(char[] table, Scanner sc, char player){
        int xCoordinate = 0;
        int yCoordinate = 0;
        do {
            System.out.print("Enter the coordinates: ");
            xCoordinate = getCoordinate(sc);
            if (xCoordinate == 0) {
                System.out.println("You should enter numbers!");
                continue;
            }
            yCoordinate = getCoordinate(sc);
            if (yCoordinate == 0) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (!isValidCoordinates(xCoordinate, yCoordinate)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (isOccupied(table, xCoordinate, yCoordinate)) {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } while (!isValidCoordinates(xCoordinate, yCoordinate) ||
                isOccupied(table, xCoordinate, yCoordinate));
        table[yCoordinate + (xCoordinate - 1) * 3 - 1] = player;
    }
    public static char getNextPlayer(char player) {
        if (player == 'X') {
            return 'O';
        } else return  'X';
    }
    public static void main(String[] args) {
        char currentPlayer = 'O';
        Scanner sc = new Scanner(System.in);
        char[] table = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        printCells(table);
        do {
            currentPlayer = getNextPlayer(currentPlayer);
            doStep(table, sc, currentPlayer);
            printCells(table);
        } while (!isWin(table, currentPlayer) && isEmptyCells(table));
        printCells(table);
        printStatus(table);
    }
}

