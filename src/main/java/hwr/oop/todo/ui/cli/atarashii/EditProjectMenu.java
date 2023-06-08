package hwr.oop.todo.ui.cli.atarashii;

import java.io.PrintStream;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

public class EditProjectMenu extends InputOptionsMenu {
	public final MenuActionHandler actionHandler;
	private InputOptionsMenu parentMenu;
	private Project projectToBeEdited;

	public EditProjectMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu, Project toBeEdited) {
		this.out = out;
		this.in = in;
		this.parentMenu = parentMenu;
		this.projectToBeEdited = toBeEdited;
		
		actionHandler = new MenuActionHandler(1, out, in);
		actionHandler.addAction("Edit title", null);
		actionHandler.addAction("Edit deadline", null);
		actionHandler.addAction("Delete project", null);
		actionHandler.addAction("Go back..", parentMenu::returnToMe);
	}
	
	public final String[] menuHeadline = {"Edit Project: %s", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  ""};
	
	@Override
	public void open() {	
		printMenu(out, actionHandler, menuHeadline, projectToBeEdited.title());
	}
}
