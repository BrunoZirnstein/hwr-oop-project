package hwr.oop.todo.ui.cli.atarashii;

import java.io.PrintStream;

import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

public class DisplayTaskMenu extends InputOptionsMenu {

	public final MenuActionHandler actionHandler;
	private InputOptionsMenu parentMenu;

	public DisplayTaskMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
		this.out = out;
		this.in = in;
		this.parentMenu = parentMenu;
		
		actionHandler = new MenuActionHandler(1, out, in);
		actionHandler.addAction("Display all tasks", null);
		actionHandler.addAction("Display tasks by project", null);
		actionHandler.addAction("Display tasks by tag", null);
		actionHandler.addAction("Display tasks by title", null);
		actionHandler.addAction("Go back..", parentMenu::returnToMe);
	}
	
	public final String[] menuHeadline = {"Display Task Menu", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  ""};
	
	@Override
	public void open() {	
		printMenu(out, actionHandler, menuHeadline, null);
	}

}
