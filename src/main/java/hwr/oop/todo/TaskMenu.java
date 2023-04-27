package hwr.oop.todo;

public class TaskMenu {
	
	private TodoMainMenu todoMainMenu = null;
	
	public TaskMenu(TodoMainMenu todoMainMenu)
	{
		this.todoMainMenu = todoMainMenu;
	}
	
	public void openCreateSimple()
	{
		Console.clear();
		
		String taskName = promptTaskName();
		
		Task newTask = new Task.Builder(taskName).build();
		Main.activeTodo.addTask(newTask);
		
		System.out.println();
		System.out.println("Task '" + taskName + "' sucessfully created.");
		todoMainMenu.returnToMe();
	}
	
	private String promptTaskName()
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
