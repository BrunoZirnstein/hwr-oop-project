package hwr.oop.todo.ui.cli.atarashii;

import java.io.PrintStream;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

public class EditTaskMenu extends InputOptionsMenu {

	public final MenuActionHandler actionHandler;
	private InputOptionsMenu parentMenu;
	private Task taskToEdit;

	public EditTaskMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu, Task toBeEdited) {
		this.out = out;
		this.in = in;
		this.parentMenu = parentMenu;
		this.taskToEdit = toBeEdited;
		
		actionHandler = new MenuActionHandler(1, out, in);
		actionHandler.addAction("Edit title", null);
		actionHandler.addAction("Edit tags", null);
		actionHandler.addAction("Edit description", null);
		actionHandler.addAction("Edit deadline", null);
		actionHandler.addAction("Edit status", null);
		actionHandler.addAction("Edit priority", null);
		actionHandler.addAction("Change assigned project", null);
		actionHandler.addAction("Go back..", parentMenu::returnToMe);
	}
	
	public final String[] menuHeadline = {"Edit Task: %s", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  "",
									 	  "[Edit Task Menu]"};
	
	@Override
	public void open() {
		printMenu(out, actionHandler, menuHeadline, taskToEdit.title());
	}

}
