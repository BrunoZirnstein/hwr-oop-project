package hwr.oop.todo.ui.cli.atarashii;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

import java.io.PrintStream;

public class MainMenu extends InputOptionsMenu {

    public final MenuActionHandler actionHandler;
    public final String[] menuHeadline = {"ToDo-List of: %s",
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
            "",
            "[MainMenu]"};

    public MainMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
        this.out = out;
        this.in = in;

        actionHandler = new MenuActionHandler(1, out, in);
        actionHandler.addAction("Create Task [quick]", null);
        actionHandler.addAction("Create task [detailed]", null);
        actionHandler.addAction("Show Tasks", null);
        actionHandler.addAction("Edit Tasks", null);
        actionHandler.addAction("Create Project", null);
        actionHandler.addAction("Edit Project", null);
        actionHandler.addAction("Show Project", null);
        actionHandler.addAction("Edit Todo-List", null);
        actionHandler.addAction("Go Back", parentMenu::returnToMe);
    }

    @Override
    public void open() {
        if (Main.activeTodo().owner().isPresent()) {
            printMenu(out, actionHandler, menuHeadline, Main.activeTodo().owner().get());
        } else {
            printMenu(out, actionHandler, menuHeadline, null);
        }
    }

}
