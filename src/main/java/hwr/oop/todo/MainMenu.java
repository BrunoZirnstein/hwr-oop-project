package hwr.oop.todo;

public class MainMenu {
	
	public static void open() {
		System.out.println("Welcome to the ultimate-u-never-forget ToDo List");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println("[MainMenu]");
		System.out.println("What is it that you want to do? To proceed further, enter the action code given inside the [ ]");
		System.out.println("[1] Create Project");
		System.out.println("[2] Delete Project");
		System.out.println("[3] Create Tasks (quick create)");
		System.out.println("[4] Create Task (detailed)");
		System.out.println("[5] Quit");
		
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
				
			case 5:
				System.out.println("Shutting down.");
				break;
				
			default:
				System.out.println("Invalid ID. Please enter a valid ID!");
				promptInput();
				break;
		}
	}

}