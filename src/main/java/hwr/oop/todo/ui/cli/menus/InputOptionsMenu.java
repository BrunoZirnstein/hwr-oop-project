package hwr.oop.todo.ui.cli.menus;

import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

import java.io.PrintStream;

/*
 * Class for Menu-Classes which serves as a main-menu,
 * Menus that just let's the user enter numbers to do something
 */
public abstract class InputOptionsMenu {
    protected PrintStream out;
    protected InputHandler in;

    /**
     * A shortcut to print the menu with headline and input options, as well as handle the user input.
     *
     * @param actionHandler       MenuActionHandler which handles the input and prints the available input options.
     * @param headline            Headline to be printed for the menu
     * @param insertHeadlineValue If the headline contains a placeholder, this String will be inserted. If null is passed, nothing is inserted.
     */
    public static void printMenu(PrintStream out, MenuActionHandler actionHandler, String[] headline, String insertHeadlineValue) {
        String printHeadline = String.join(System.lineSeparator(), headline);

        if (insertHeadlineValue != null) {
            printHeadline = String.format(printHeadline, insertHeadlineValue);
        }

        out.println(printHeadline);

        actionHandler.printMenu();

        actionHandler.promptAndHandleInput();
    }

    public abstract void open();

    /**
     * Let's the user press ENTER, clears the console and opens the menu.
     * This method is specifically designed for sub-menus, which will return the super-menu.
     */
    public void returnToMe() {
        if (!Console.enterToContinue(out, in)) {
            return;
        }

        Console.clear(out);
        open();
    }
}
