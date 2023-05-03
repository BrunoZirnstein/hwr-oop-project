package hwr.oop.todo;

import java.io.IOException;

public class ManageToDoMenu {
	public static void openCreate()
	{
		Console.clear();
		
		String todoName = promptToDoName();
		
		Main.activeTodo = new ToDo(todoName);
		System.out.println("Created the ToDo-List: '" + todoName + "' sucessfully.");
		
		TodoMainMenu.returnToMe();
	}
	
	private static String promptToDoName()
	{
		System.out.println("Enter your name / the name of the desired ToDo List.");
		
		String todoName;
		while(true)
		{
			Console.displayInputIndicator();
			todoName = Console.input.nextLine();
			
			if(todoName.isEmpty() == false)
			{
				return todoName;
			}
			else
			{
				System.out.println("Invalid name (empty). Please enter a valid name!");
			}
		}
	}
	
	public static void openLoad()
	{
		Console.clear();

		String todoName = promptToDoName();
		Main.activeTodo = new ToDo(todoName);
		System.out.println("To load a ToDo for the current user!");
		ToDo todo = null;
		CSVReader reader = new CSVReader();
		todo = reader.readToDoFile(Main.activeTodo.user());
		System.out.println("Task names for " + todo.user() + ":");
		for (Task task : todo.tasks()) {
			System.out.println(task.title());
		}
		// Niklas: load function call with todoName which serves as the 'user' and get a ToDo object to override Main.activeToDo.
		
		
	}
		
	public static void openRemove()
	{
		
	}
}
