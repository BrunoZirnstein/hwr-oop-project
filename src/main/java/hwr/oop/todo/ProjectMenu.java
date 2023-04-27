package hwr.oop.todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ProjectMenu{

	
	public static void openCreate()
	{
		Console.clear();
		
		String newProjectName = promptProjectNameInput();
		
		LocalDate deadline = promptDeadlineInput();
		
		Project newProject = new Project(newProjectName, deadline);
		Main.activeTodo.createProject(newProject);
		
		System.out.println();
		System.out.println("Project '" + newProjectName + "' sucessfully created.");
		TodoMainMenu.returnToMe();
	}
	
	/**
	 * Prompts the user to enter a name for the project and handles if the user enters invalid names (e.g. empty string)
	 * @return The user entered project name
	 */
	private static String promptProjectNameInput()
	{
		System.out.println("What's the name of the project?");
		
		String projectName;
		while(true)
		{
			Console.displayInputIndicator();
			projectName = Console.input.nextLine();
			
			if(projectName.isEmpty() == false)
			{
				return projectName;
			}
			else
			{
				System.out.println("Invalid project name (empty). Please enter a valid project name!");
			}
		}
	}
	
	/**
	 * Prompts the user to enter a deadline for the project and handles invalid input (e.g. wrong date input format).
	 * @return The user entered deadline
	 */
	private static LocalDate promptDeadlineInput()
	{
		System.out.println("What's the deadline of the project? Format: YYYY-MM-DD (or leave blank)");
		
		String deadlineStr = null;
		LocalDate deadline = null;
		while(true)
		{
			Console.displayInputIndicator();
			deadlineStr = Console.input.nextLine();
			
			if(deadlineStr.isEmpty() == false)
			{
				try {
					deadline = LocalDate.parse(deadlineStr);
					return deadline;
				}
				catch (DateTimeParseException ex) {
					System.out.println("Invalid date format! Please enter the deadline in the Format: YYYY-MM-DD (or leave blank)");
				}
			}
			else
			{
				return null;
			}
		}
	}
	
}
