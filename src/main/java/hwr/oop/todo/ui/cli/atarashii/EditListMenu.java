package hwr.oop.todo.ui.cli.atarashii;

import java.io.PrintStream;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

public class EditListMenu extends InputOptionsMenu{
	public final MenuActionHandler actionHandler;
	private InputOptionsMenu parentMenu;

	public EditListMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
		this.out = out;
		this.in = in;
		this.parentMenu = parentMenu;
		
		actionHandler = new MenuActionHandler(1, out, in);
		actionHandler.addAction("Change ToDo-List Owner / Name", null);
		actionHandler.addAction("Delete ToDo-List", null);
		actionHandler.addAction("Go back..", parentMenu::returnToMe);
	}
	
	public final String[] menuHeadline = {"Edit Todo-List: %s", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  ""};
	
	@Override
	public void open() {	
		printMenu(out, actionHandler, menuHeadline, Main.activeTodo.owner().get());
	}
}
