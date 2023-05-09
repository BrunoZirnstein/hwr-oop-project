package hwr.oop.todo;

import java.io.IOException;

public class TodoMainMenu {
	
	public static void open()
	{
		System.out.println("ToDo List of: " + Main.activeTodo.user());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println("[ToDo MainMenu]");
		System.out.println("What is it that you want to do? To proceed further, enter the action code given inside the [ ]");
		System.out.println("[1] Create Project");
		System.out.println("[2] Delete Project");
		System.out.println("[3] Create Tasks (quick create)");
		System.out.println("[4] Create Task (detailed)");
		System.out.println("[5] Save ToDo List in file..");
		System.out.println("[6] Go back");
		
		promptInput();
	}
	
	/**
	 * Just a little function that lets the user return to the MainMenu (reopen's it) by
	 * letting the user press any key in order to return to the MainMenu.
	 */
	public static void returnToMe()
	{
		Console.EnterToContinue();
		Console.clear();
		open();
	}
	
	/**
	 * Prompts and waits for user console input and handles invalid input (= a not integer input).
	 */
	private static void promptInput()
	{
		Console.displayInputIndicator();
		int inputID = 0;
		while(inputID == 0) {
			try {
				inputID = Integer.parseInt(Console.input.nextLine());			
			}
			catch(NumberFormatException ex) {
				System.out.println("Invalid input. Please try again");
			}			
		}
		
		handleInput(inputID);
	}
	
	private static void handleInput(int input)
	{
		switch(input)
		{
			case 1:
				ProjectMenu.openCreate();
				break;
				
			case 2:
				break;
				
			case 3:
				TaskMenu.openCreateSimple();
				break;
				
			case 5: // Niklas: save current to-do list in file -> Main.activeToDo
				//CSVCreate.writeToDoFile(,Main.activeTodo.user()) (Task task, ToDo todo, String filePathToDo)
				for (Task element : Main.activeTodo.tasks()){
					try {
						CSVCreate.writeToDoFile(element, Main.activeTodo, CSVCreate.getFilePathTodo());
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				break;
				
			case 6:
				MainMenu.returnToMe();
				break;
				
			default:
				System.out.println("Invalid ID. Please enter a valid ID!");
				promptInput();
				break;
		}
	}
}
