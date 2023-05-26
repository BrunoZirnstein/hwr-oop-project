package hwr.oop.todo.ui.cli;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.application.Task;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TaskMenu {
	private PrintStream out = null;
	private Scanner in = null;
	
	private TodoMainMenu todoMainMenu = null;
	
	public TaskMenu(TodoMainMenu todoMainMenu, OutputStream out, InputStream in) {
		this.out = new PrintStream(out);
		this.in = new Scanner(in);
		
		this.todoMainMenu = todoMainMenu;
	}
	
	public void openCreateSimple() {
		Console.clear(out);
		
		String taskName = promptTaskName();
		
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
			
			if(userInput.isEmpty() == false)
			{
				return userInput;
			}
			else
			{
				out.println();
			}
		}
	}
	
}
