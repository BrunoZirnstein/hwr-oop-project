package hwr.oop.todo.ui.cli;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class MenuActionHandler {
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Please try again";
    public static final String WRONG_INPUT_ID_MESSAGE = "Invalid ID. Please enter a valid ID!";

    private final HashMap<Integer, Runnable> actionMap;
    private final HashMap<Integer, String> actionDescriptionsMap;
    private final PrintStream out;
    private final InputHandler in;

    private int currentIndex;
    private final int actionStartIndex;

    public MenuActionHandler(int startWithActionIndex, PrintStream out, InputHandler in) {
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

    public String getMenuPrintString() {
        StringBuilder menuString = new StringBuilder();

        for (Map.Entry<Integer, String> entry : actionDescriptionsMap.entrySet()) {
            menuString.append("[").append(entry.getKey()).append("] ").append(entry.getValue()).append(System.lineSeparator());
        }

        return menuString.toString();
    }

    public void printMenu() {
        out.println(getMenuPrintString());
    }

    public void promptAndHandleInput() {
        int inputID = -1;
        while (inputID == -1) {
            Console.displayInputIndicator(out);
            try {
                String result = in.nextLine();
                if (result == null) {
                    return;
                }

                inputID = Integer.parseInt(result);
            } catch (NumberFormatException ex) {
                out.println(INVALID_INPUT_MESSAGE);
            }
        }

        handleInput(inputID);
    }

    public void handleInput(int userInput) {
        if (!actionMap.containsKey(userInput)) {
            out.println(WRONG_INPUT_ID_MESSAGE);
            promptAndHandleInput();
            return;
        }

        Runnable action = actionMap.get(userInput);
        action.run();
    }

    public int getCount() {
        return currentIndex - actionStartIndex;
    }
}
