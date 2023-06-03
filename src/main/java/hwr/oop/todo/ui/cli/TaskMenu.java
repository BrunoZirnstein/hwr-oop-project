package hwr.oop.todo.ui.cli;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.core.Task;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TaskMenu {
	private PrintStream out = null;
	private InputHandler in = null;
	
	private TodoMainMenu todoMainMenu = null;
	
	public TaskMenu(TodoMainMenu todoMainMenu, OutputStream out, InputHandler in) {
		this.out = new PrintStream(out);
		this.in = in;
		
		this.todoMainMenu = todoMainMenu;
	}
	
	public final String openCreateSimpleSuccess_msg = System.lineSeparator() + "Task '{}' sucessfully created.";
	public void openCreateSimple() {
		Console.clear(out);
		
		String taskName = promptTaskName();
		
		if(taskName == null) {
			return;
		}
		
		Task newTask = new Task.Builder(taskName).build();
		Main.activeTodo.addTask(newTask);
		
		out.println(String.format(openCreateSimpleSuccess_msg, taskName));
		todoMainMenu.returnToMe();
	}
	
	public final String promptTaskName_msg = "Enter a name for the task.";
	public final String promptTaskName_invalidNameMsg = "Invalid (empty) name. Please enter a new name.";
	private String promptTaskName() {
		out.println(promptTaskName_msg);
		
		String userInput = null;
		while(true)
		{
			Console.displayInputIndicator(out);
			userInput = in.nextLine();
			
			if(userInput == null) {
				return null;
			}
			else if(!userInput.isEmpty()) {
				return userInput;
			}
			else {
				out.println(promptTaskName_invalidNameMsg);
			}
		}
	}
	
}
