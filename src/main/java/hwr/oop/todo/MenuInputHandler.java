package hwr.oop.todo;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class MenuInputHandler {
    private final HashMap<Integer, Runnable> actionMap;
    private final HashMap<Integer, String> actionDescriptionsMap;
    private PrintStream out = null;
    private Scanner in = null;

    private int currentIndex = 0;
    private int actionStartIndex = 0;

    public MenuInputHandler(int startWithActionIndex, PrintStream out,
                            Scanner in) {
        actionMap = new HashMap<>();
        actionDescriptionsMap = new HashMap<>();
        currentIndex = startWithActionIndex;
        actionStartIndex = startWithActionIndex;

        this.out = out;
        this.in = in;
    }

    public void addAction(String description, Runnable action) {
        actionMap.put(currentIndex, action);
        actionDescriptionsMap.put(currentIndex, description);

        currentIndex += 1;
    }

    public void printMenu() {
        for (int i = actionStartIndex; i < currentIndex; i++) {
            out.println("[" + i + "] " + actionDescriptionsMap.get(i));
        }
    }

    public void propmtAndHandleInput() {
        int inputID = 0;
        while (inputID == 0) {
            Console.displayInputIndicator();
            try {
                inputID = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException ex) {
                out.println("Invalid input. Please try again");
            }
        }

        handleInput(inputID);
    }

    public void handleInput(int userInput) {
        if (!actionMap.containsKey(userInput)) {
            out.println("Invalid ID. Please enter a valid ID!");
            propmtAndHandleInput();
            return;
        }

        Runnable action = actionMap.get(userInput);
        action.run();
    }
}
