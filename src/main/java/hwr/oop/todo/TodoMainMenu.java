package hwr.oop.todo;

import java.util.Scanner;

public class TodoMainMenu {
	
	private MainMenu mainMenu = null;
	private ProjectMenu projectMenu = null;
	private TaskMenu taskMenu = null;
	
	private MenuInputHandler inputHandler = null;
	
	public TodoMainMenu(MainMenu mainMenu)
	{
		if(mainMenu == null)
		{
			throw new NullPointerException("the mainMenu parameter is invalid!");
		}
		this.mainMenu = mainMenu;
		
		projectMenu = new ProjectMenu(this);
		taskMenu = new TaskMenu(this);
		
		inputHandler = new MenuInputHandler(1, System.out, new Scanner(System.in));
		inputHandler.addAction("Create Project", () -> projectMenu.openCreate());
		inputHandler.addAction("Delete Project", null);
		inputHandler.addAction("Create Tasks (quick create)", () -> taskMenu.openCreateSimple());
		inputHandler.addAction("Create Task (detailed)", null);
		inputHandler.addAction("Save ToDo List in file..", null);	// Execute Niklas function here
		inputHandler.addAction("Go back", () -> mainMenu.returnToMe());
	}
	
	public void open()
	{
		System.out.println("ToDo List of: " + Main.activeTodo.user());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println("[ToDo MainMenu]");
		System.out.println("What is it that you want to do? To proceed further, enter the action code given inside the [ ]");
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
