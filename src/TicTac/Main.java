package TicTac;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new TicTac().game();
    }
}

class TicTac {
    final char cell_x = 'x';
    final char cell_o = 'o';
    final char empty_field = '.';
    char[][] table  = new char[3][3];
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    void game() {
        initializeTable();
        while (true) {
            yourTurn();
            if (checkWin(cell_x)) {
                System.out.println("Вы победили!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ничья!");
                break;
            }
            turnAI();
            printTable();
            if (checkWin(cell_o)) {
                System.out.println("ИИ победил!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
        System.out.println("Игра завершена.");
        printTable();
    }

    void initializeTable() {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                table[row][column] = empty_field;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3)
            return true;
        return table[y][x] != empty_field;
    }

    boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int column = 0; column < 3; column++)
                if (table[row][column] == empty_field)
                    return false;
        return true;
    }

    void yourTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты символа(в виде x y):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            if (isCellValid(x, y))
                System.out.println("Повторите попытку.");
        } while (isCellValid(x, y));
        table[y][x] = cell_x;
    }

    void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (isCellValid(x, y));
        table[y][x] = cell_o;
    }

    boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][2] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[2][i] == dot))
                return true;
        return (table[0][0] == dot && table[1][1] == dot &&
                table[2][2] == dot) ||
                (table[2][0] == dot && table[1][1] == dot &&
                        table[0][2] == dot);
    }

    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++)
                System.out.print(table[row][column] + " ");
            System.out.println();
        }
    }
}

