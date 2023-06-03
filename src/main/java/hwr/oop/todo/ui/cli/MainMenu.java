package hwr.oop.todo.ui.cli;

import java.io.OutputStream;
import java.io.PrintStream;

public class MainMenu {
	private PrintStream out = null;
	private InputHandler in = null;
	
	public final MenuActionHandler inputHandler;
	private ListMenu manageToDoMenu;
	
	public final String[] menuHeadline = {"Welcome to the ultimate-u-never-forget ToDo List", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  "",
									 	  "[MainMenu]"};
	
	
	public MainMenu(OutputStream out, InputHandler in) {
		this.out = new PrintStream(out);
        this.in = in;
		manageToDoMenu = new ListMenu(this, out, in);
		// https://pitest.org/quickstart/maven/#mutators
		inputHandler = new MenuActionHandler(1, this.out, this.in);
		inputHandler.addAction("Create&Load ToDo List", () -> manageToDoMenu.openCreate());
		inputHandler.addAction("Load ToDo List from file", () -> manageToDoMenu.openLoad());
		inputHandler.addAction("Delete ToDO List", null);
		inputHandler.addAction("Quit", () -> this.out.println("Shutting down."));
	}
	
	public void open() {
		out.println(String.join(System.lineSeparator(), menuHeadline));
		
		inputHandler.printMenu();
		
		inputHandler.propmtAndHandleInput();
	}
	
	/**
	 * Just a little function that lets the user return to the MainMenu (reopen's it) by
	 * letting the user press any key in order to return to the MainMenu.
	 */
	public void returnToMe() {
		if(Console.enterToContinue(out, in) == false) {
			return;
		}
		
		Console.clear(out);
		open();
	}

}