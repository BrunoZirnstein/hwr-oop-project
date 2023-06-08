package hwr.oop.todo.ui.cli.atarashii;

import java.io.PrintStream;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

public class MainMenu extends InputOptionsMenu{

	public final MenuActionHandler actionHandler;
	private InputOptionsMenu parentMenu;
	
	public MainMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
		this.out = out;
		this.in = in;
		this.parentMenu = parentMenu;
		
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
	
	public final String[] menuHeadline = {"ToDo-List of: %s", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  "",
									 	  "[MainMenu]"};
	
	
	@Override
	public void open() {
		printMenu(out, actionHandler, menuHeadline, Main.activeTodo.owner().get());
	}

}
