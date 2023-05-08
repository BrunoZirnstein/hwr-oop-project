package hwr.oop.todo;

import java.util.Scanner;

public class MainMenu {
	private MenuInputHandler inputHandler = null;
	private ManageToDoMenu manageToDoMenu;
	
	
	public MainMenu()
	{
		manageToDoMenu = new ManageToDoMenu(this);
		
		inputHandler = new MenuInputHandler(1, System.out, new Scanner(System.in));
		inputHandler.addAction("Create&Load ToDo List", () -> manageToDoMenu.openCreate());
		inputHandler.addAction("Load ToDo List from file", () -> manageToDoMenu.openLoad());
		inputHandler.addAction("Delete ToDO List", null);
		inputHandler.addAction("Quit", () -> System.out.println("Shutting down."));
	}
	
	public void open() {
		System.out.println("Welcome to the ultimate-u-never-forget ToDo List");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println("[MainMenu]");
		inputHandler.printMenu();
		
		inputHandler.propmtAndHandleInput();
	}
	
	/**
	 * Just a little function that lets the user return to the MainMenu (reopen's it) by
	 * letting the user press any key in order to return to the MainMenu.
	 */
	public void returnToMe()
	{
		Console.EnterToContinue();
		Console.clear();
		open();
	}

}