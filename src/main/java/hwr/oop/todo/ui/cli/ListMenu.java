package hwr.oop.todo.ui.cli;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.core.ToDoList;
import java.io.OutputStream;
import java.io.PrintStream;

public class ListMenu {
	private PrintStream out = null;
	private InputHandler in = null;

	private TodoMainMenu todoMainMenu = null;
	
	
	public ListMenu(MainMenu mainMenu, OutputStream out, InputHandler in) {
		this.out = new PrintStream(out);
		this.in = in;

		todoMainMenu = new TodoMainMenu(mainMenu, out, in);
	}

	public final String todoCreatedSucess_msg = "Created the ToDo-List: '{}' sucessfully.";
	public void openCreate() {
		Console.clear(out);

		String todoName = promptToDoName();

		Main.activeTodo = new ToDoList(todoName);
		
		out.println(String.format(todoCreatedSucess_msg, todoName));

		todoMainMenu.returnToMe();
	}

	public final String todoNameInputPrompt_msg = "Enter your name / the name of the desired ToDo List.";
	public final String toNamePrompt_emptyInputMsg = "Invalid name (empty). Please enter a valid name!";
	private String promptToDoName() {
		out.println(todoNameInputPrompt_msg);

		String todoName;
		while (true) {
			Console.displayInputIndicator(out);
			todoName = in.nextLine();

			if (todoName == null) {
				return null;
			} else if (todoName.isEmpty() == false) {
				return todoName;
			} else {
				out.println(toNamePrompt_emptyInputMsg);
			}
		}
	}

	public final String openLoadPrompt_msg = "To load a ToDo list";
	public void openLoad() {
		Console.clear(out);

		out.println(openLoadPrompt_msg);
		String todoName = promptToDoName();

		// Niklas: load function call with todoName which serves as the 'user' and get a
		// ToDo object to override Main.activeToDo.
		/*
		 * try {
		 * CSVReader reader = new CSVReader();
		 * Main.activeTodo = reader.readToDoFile(todoName, null);
		 * todoMainMenu.returnToMe();
		 * }
		 * catch (Exception e) {
		 * out.println("Something horrible happend, the todo list was not found (maybe)"
		 * );
		 * mainMenu.returnToMe();
		 * }
		 * 
		 */
	}

	public void openRemove() {

	}
}
