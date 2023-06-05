package hwr.oop.todo.ui.cli;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.core.Task;

import java.io.OutputStream;
import java.io.PrintStream;

public class TaskMenu {
	private PrintStream out = null;
	private InputHandler in = null;
	
	private TodoMainMenu todoMainMenu = null;
	
	public TaskMenu(TodoMainMenu todoMainMenu, OutputStream out, InputHandler in) {
		this.out = new PrintStream(out);
		this.in = in;
		
		this.todoMainMenu = todoMainMenu;
	}
	
	public final String openCreateSimpleSuccessMsg = System.lineSeparator() + "Task '{}' sucessfully created.";
	public void openCreateSimple() {
		Console.clear(out);
		
		String taskName = promptTaskName();
		
		if(taskName == null) {
			return;
		}
		
		Task newTask = new Task.Builder(taskName).build();
		Main.activeTodo.addTask(newTask);
		
		out.println(String.format(openCreateSimpleSuccessMsg, taskName));
		todoMainMenu.returnToMe();
	}
	
	public static final String PROMPT_TASK_NAME_MSG = "Enter a name for the task.";
	public static final String PROMPT_TASK_NAME_INVALID_NAME_MSG = "Invalid (empty) name. Please enter a new name.";
	private String promptTaskName() {
		out.println(PROMPT_TASK_NAME_MSG);
		
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
				out.println(PROMPT_TASK_NAME_INVALID_NAME_MSG);
			}
		}
	}
	
}
