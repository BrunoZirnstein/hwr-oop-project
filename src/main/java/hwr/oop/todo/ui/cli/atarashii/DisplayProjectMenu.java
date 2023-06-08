package hwr.oop.todo.ui.cli.atarashii;

import java.io.PrintStream;

import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

public class DisplayProjectMenu extends InputOptionsMenu{
	public final MenuActionHandler actionHandler;
	private InputOptionsMenu parentMenu;

	public DisplayProjectMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
		this.out = out;
		this.in = in;
		this.parentMenu = parentMenu;
		
		actionHandler = new MenuActionHandler(1, out, in);
		actionHandler.addAction("All projects", null);
		actionHandler.addAction("Projects by title", null);
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