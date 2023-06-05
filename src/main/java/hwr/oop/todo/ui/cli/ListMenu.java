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

	public static final String TODO_CREATED_SUCCESS_MSG = "Created the ToDo-List: '{}' successfully.";
	public void openCreate() {
		Console.clear(out);

		String todoName = promptToDoName();

		Main.activeTodo = new ToDoList(todoName);
		
		out.println(String.format(TODO_CREATED_SUCCESS_MSG, todoName));

		todoMainMenu.returnToMe();
	}

	public static final String TODO_NAME_INPUT_PROMPT_MSG = "Enter your name / the name of the desired ToDo List.";
	public static final String TO_NAME_PROMPT_EMPTY_INPUT_MSG = "Invalid name (empty). Please enter a valid name!";
	private String promptToDoName() {
		out.println(TODO_NAME_INPUT_PROMPT_MSG);

		String todoName;
		while (true) {
			Console.displayInputIndicator(out);
			todoName = in.nextLine();

			if (todoName == null) {
				return null;
			} else if (!todoName.isEmpty()) {
				return todoName;
			} else {
				out.println(TO_NAME_PROMPT_EMPTY_INPUT_MSG);
			}
		}
	}

	public static final String OPEN_LOAD_PROMPT_MSG = "To load a ToDo list";
	public void openLoad() {
		Console.clear(out);

		out.println(OPEN_LOAD_PROMPT_MSG);
		String todoName = promptToDoName();

		// Niklas: load function call with todoName which serves as the 'user' and get a
		// To-Do object to override Main.activeToDo.
		/*
		 * try {
		 * CSVReader reader = new CSVReader();
		 * Main.activeTodo = reader.readToDoFile(todoName, null);
		 * todoMainMenu.returnToMe();
		 * }
		 * catch (Exception e) {
		 * out.println("Something horrible happend, the to-do list was not found (maybe)"
		 * );
		 * mainMenu.returnToMe();
		 * }
		 * 
		 */
	}

	public void openRemove() {

	}
}
