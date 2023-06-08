package hwr.oop.todo.ui.cli;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.core.Project;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ProjectMenu{
	private PrintStream out = null;
	private InputHandler in = null;
	
	private TodoMainMenu todoMainMenu = null;
	
	public ProjectMenu(TodoMainMenu todoMainMenu, OutputStream out, InputHandler in) {
		this.out = new PrintStream(out);
		this.in = in;
		
		this.todoMainMenu = todoMainMenu;
	}
	
	public final String openCreate_successMsg = System.lineSeparator() + "Project '{}' sucessfully created.";
	public void openCreate() {
		Console.clear(out);
		
		String newProjectName = promptProjectNameInput();
		
		LocalDate deadline = promptDeadlineInput();
		
		if(deadline == null && newProjectName == null) {
			return;
		}
		
		Project newProject = new Project.Builder(newProjectName).deadline(deadline).build();
		Main.activeTodo.addProject(newProject);
		
		out.println(openCreate_successMsg);
		todoMainMenu.returnToMe();
	}
	
	public final String promptProjectNameInput_msg = "What's the name of the project?";
	public final String promptProjectNameInput_invalidInputMsg = "Invalid project name (empty). Please enter a valid project name!";
	/**
	 * Prompts the user to enter a name for the project and handles if the user enters invalid names (e.g. empty string)
	 * @return The user entered project name
	 */
	private String promptProjectNameInput() {
		out.println(promptProjectNameInput_msg);
		
		String projectName;
		while(true)
		{
			Console.displayInputIndicator(out);
			projectName = in.nextLine();
			
			if(projectName == null) {
				return null;
			} 
			else if(projectName.isEmpty() == false) {
				return projectName;
			} 
			else {
				out.println(promptProjectNameInput_invalidInputMsg);
			}
		}
	}
	
	public final String promptDeadlineInput_msg = "What's the deadline of the project? Format: YYYY-MM-DD (or leave blank)";
	public final String promptDeadlineInput_InvalidInputErrormMsg = "Invalid date format! Please enter the deadline in the Format: YYYY-MM-DD (or leave blank)";
	/**
	 * Prompts the user to enter a deadline for the project and handles invalid input (e.g. wrong date input format).
	 * @return The user entered deadline
	 */
	private LocalDate promptDeadlineInput() {
		out.println(promptDeadlineInput_msg);
		
		String deadlineStr = null;
		LocalDate deadline = null;
		while(true)
		{
			Console.displayInputIndicator(out);
			deadlineStr = in.nextLine();
			
			if(deadlineStr == null) {
				return null;
			}
			
			if(!deadlineStr.isEmpty())
			{
				try {
					deadline = LocalDate.parse(deadlineStr);
					return deadline;
				}
				catch (DateTimeParseException ex) {
					out.println(promptDeadlineInput_InvalidInputErrormMsg);
				}
			}
			else
			{
				return null;
			}
		}
	}
	
}
