package hwr.oop.todo;

public class TaskMenu {
	
	public static void openCreateSimple()
	{
		Console.clear();
		
		String taskName = promptTaskName();
		
		Task newTask = new Task.Builder(taskName).build();
		
		System.out.println();
		System.out.println("Task '" + taskName + "' sucessfully created.");
		MainMenu.returnToMe();
	}
	
	private static String promptTaskName()
	{
		System.out.println("Enter a name for the task.");
		
		String userInput = null;
		while(true)
		{
			Console.displayInputIndicator();
			userInput = Console.input.nextLine();
			
			if(userInput.isEmpty() == false)
			{
				return userInput;
			}
			else
			{
				System.out.println();
			}
		}
	}
	
}
