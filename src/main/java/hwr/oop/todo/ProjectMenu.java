package hwr.oop.todo;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ProjectMenu{
	private PrintStream out = null;
	private Scanner in = null;
	
	private TodoMainMenu todoMainMenu = null;
	
	public ProjectMenu(TodoMainMenu todoMainMenu, OutputStream out, InputStream in) {
		this.out = new PrintStream(out);
		this.in = new Scanner(in);
		
		this.todoMainMenu = todoMainMenu;
	}
	
	public void openCreate() {
		Console.clear(out);
		
		String newProjectName = promptProjectNameInput();
		
		LocalDate deadline = promptDeadlineInput();
		
		Project newProject = new Project(newProjectName, deadline);
		Main.activeTodo.addProject(newProject);
		
		out.println();
		out.println("Project '" + newProjectName + "' sucessfully created.");
		todoMainMenu.returnToMe();
	}
	
	/**
	 * Prompts the user to enter a name for the project and handles if the user enters invalid names (e.g. empty string)
	 * @return The user entered project name
	 */
	private String promptProjectNameInput() {
		out.println("What's the name of the project?");
		
		String projectName;
		while(true)
		{
			Console.displayInputIndicator(out);
			projectName = in.nextLine();
			
			if(projectName.isEmpty() == false)
			{
				return projectName;
			}
			else
			{
				out.println("Invalid project name (empty). Please enter a valid project name!");
			}
		}
	}
	
	/**
	 * Prompts the user to enter a deadline for the project and handles invalid input (e.g. wrong date input format).
	 * @return The user entered deadline
	 */
	private LocalDate promptDeadlineInput() {
		out.println("What's the deadline of the project? Format: YYYY-MM-DD (or leave blank)");
		
		String deadlineStr = null;
		LocalDate deadline = null;
		while(true)
		{
			Console.displayInputIndicator(out);
			deadlineStr = in.nextLine();
			
			if(deadlineStr.isEmpty() == false)
			{
				try {
					deadline = LocalDate.parse(deadlineStr);
					return deadline;
				}
				catch (DateTimeParseException ex) {
					out.println("Invalid date format! Please enter the deadline in the Format: YYYY-MM-DD (or leave blank)");
				}
			}
			else
			{
				return null;
			}
		}
	}
	
}
