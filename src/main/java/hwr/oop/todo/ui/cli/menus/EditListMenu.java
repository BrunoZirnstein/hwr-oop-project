package hwr.oop.todo.ui.cli.menus;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

import java.io.PrintStream;

public class EditListMenu extends InputOptionsMenu {
    public final MenuActionHandler actionHandler;
    public final String[] menuHeadline = {"Edit Todo-List: %s",
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
            ""};

    public EditListMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
        this.out = out;
        this.in = in;

        actionHandler = new MenuActionHandler(1, out, in);
        actionHandler.addAction("Change ToDo-List Owner / Name", null);
        actionHandler.addAction("Delete ToDo-List", null);
        actionHandler.addAction("Go back..", parentMenu::returnToMe);
    }

    @Override
    public void open() {
        Main.activeTodo().owner().ifPresentOrElse(owner -> printMenu(out, actionHandler, menuHeadline, owner),
                () -> printMenu(out, actionHandler, menuHeadline, ""));
    }
}
