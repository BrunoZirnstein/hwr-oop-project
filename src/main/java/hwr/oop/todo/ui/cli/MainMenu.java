package hwr.oop.todo.ui.cli;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MainMenu {
	private PrintStream out = null;
	private Scanner in = null;
	
	private MenuInputHandler inputHandler = null;
	private ManageToDoMenu manageToDoMenu;
	
	public final String[] menuHeadline = {"Welcome to the ultimate-u-never-forget ToDo List", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  "",
									 	  "[MainMenu]"};
	
	
	
	public MainMenu(OutputStream out, InputStream in) {
		this.out = new PrintStream(out);
        this.in = new Scanner(in);
		manageToDoMenu = new ManageToDoMenu(this, out, in);
		
		inputHandler = new MenuInputHandler(1, this.out, this.in);
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
		Console.EnterToContinue(out, in);
		Console.clear(out);
		open();
	}

}