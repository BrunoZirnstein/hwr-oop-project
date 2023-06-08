package hwr.oop.todo.ui.cli.atarashii;

import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

import java.io.PrintStream;

public class DisplayProjectMenu extends InputOptionsMenu {
    public final MenuActionHandler actionHandler;
    public final String[] menuHeadline = {"Display Task Menu",
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
            ""};

    public DisplayProjectMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
        this.out = out;
        this.in = in;

        actionHandler = new MenuActionHandler(1, out, in);
        actionHandler.addAction("All projects", null);
        actionHandler.addAction("Projects by title", null);
        actionHandler.addAction("Go back..", parentMenu::returnToMe);
    }

    @Override
    public void open() {
        printMenu(out, actionHandler, menuHeadline, null);
    }

}