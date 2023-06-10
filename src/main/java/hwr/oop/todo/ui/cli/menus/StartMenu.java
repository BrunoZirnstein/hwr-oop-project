package hwr.oop.todo.ui.cli.menus;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

import java.io.PrintStream;

public class StartMenu extends InputOptionsMenu {

    public static final String LOAD_OR_CREATE_SUCCESS_MSG_PREFIX = "Success!: ";
    public final MenuActionHandler inputHandler;
    public final String[] menuHeadline = {"Welcome to the ultimate-u-never-forget ToDo List",
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
            "",
            "[Startmenu]"};
    private final MainMenu mainMenu;

    public StartMenu(PrintStream out, InputHandler in) {
        this.out = out;
        this.in = in;

        inputHandler = new MenuActionHandler(1, out, in);
        inputHandler.addAction("Create or load Todo-List", this::loadOrCreateTodoList);
        inputHandler.addAction("Quit", this::quitProgram);

        mainMenu = new MainMenu(out, in, this);
    }

    @Override
    public void open() {
        InputOptionsMenu.printMenu(out, inputHandler, menuHeadline, null);
    }

    private void loadOrCreateTodoList() {
        String promptMsg = "Enter your name / the name of the todo list you want to create or load." +
                System.lineSeparator() + "If the Todo-List with the entered name already exists, it is being loadet." +
                System.lineSeparator() + "Otherwise it will be created.";
        String invalidInputMsg = "Empty names are not allowed. Please enter a valid name.";

        String todolistName = Console.promptForString(out, in, true, promptMsg, invalidInputMsg);

        out.println(LOAD_OR_CREATE_SUCCESS_MSG_PREFIX + todolistName + System.lineSeparator());
        Main.changeActiveTodo(new ToDoList(todolistName));

        mainMenu.open();
    }

    private void quitProgram() {
    /*
      Just an empty function for the MenuActionHandler to have a function that does nothing,
      resulting in the program to stop.
     */
    }

}
