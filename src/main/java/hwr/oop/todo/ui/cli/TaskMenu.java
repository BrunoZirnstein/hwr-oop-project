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
	
	public void openCreateSimple() {
		Console.clear(out);
		
		String taskName = promptTaskName();
		
		if(taskName == null) {
			return;
		}
		
		Task newTask = new Task.Builder(taskName).build();
		Main.activeTodo.addTask(newTask);
		
		out.println();
		out.println("Task '" + taskName + "' sucessfully created.");
		todoMainMenu.returnToMe();
	}
	
	private String promptTaskName() {
		out.println("Enter a name for the task.");
		
		String userInput = null;
		while(true)
		{
			Console.displayInputIndicator(out);
			userInput = in.nextLine();
			
			if(userInput == null) {
				return null;
			}
			else if(userInput.isEmpty() == false) {
				return userInput;
			}
			else {
				out.println();
			}
		}
	}
	
}
