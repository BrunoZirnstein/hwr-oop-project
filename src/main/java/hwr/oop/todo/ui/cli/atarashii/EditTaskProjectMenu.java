package hwr.oop.todo.ui.cli.atarashii;

import java.io.PrintStream;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

public class EditTaskProjectMenu extends InputOptionsMenu {
	public final MenuActionHandler actionHandler;
	private InputOptionsMenu parentMenu;
	private Task taskToBeEdited;

	public EditTaskProjectMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu, Task toBeEdited) {
		this.out = out;
		this.in = in;
		this.parentMenu = parentMenu;
		this.taskToBeEdited = toBeEdited;
		
		actionHandler = new MenuActionHandler(1, out, in);
		actionHandler.addAction("Assign project", null);
		actionHandler.addAction("Change project", null);
		actionHandler.addAction("Remove project", null);
		actionHandler.addAction("Go back..", parentMenu::returnToMe);
	}
	
	public final String[] menuHeadline = {"Edit Project Assignment for Task: %s", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  ""};
	
	@Override
	public void open() {	
		printMenu(out, actionHandler, menuHeadline, taskToBeEdited.title());
	}
}
