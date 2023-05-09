package hwr.oop.todo;

import java.util.Scanner;

public class Console {
    protected final static Scanner input = new Scanner(System.in);

    public static void clear() {
        for (int i = 0; i < 30; i++) {
            System.out.print(System.lineSeparator());
        }
    }

    public static void displayInputIndicator() {
        System.out.print("> ");
    }

    public static void EnterToContinue() {
        System.out.print("Press [ENTER] to continue...");
        input.nextLine();
    }
}