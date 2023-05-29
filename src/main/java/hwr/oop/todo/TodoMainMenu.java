package hwr.oop.todo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TodoMainMenu {
	private PrintStream out = null;
	private Scanner in = null;
	
	private MainMenu mainMenu = null;
	private ProjectMenu projectMenu = null;
	private TaskMenu taskMenu = null;
	
	private MenuInputHandler inputHandler = null;
	
	public TodoMainMenu(MainMenu mainMenu, OutputStream out, InputStream in) {
		this.out = new PrintStream(out);
		this.in = new Scanner(in);
		
		if(mainMenu == null)
		{
			throw new NullPointerException("the mainMenu parameter is invalid!");
		}
		this.mainMenu = mainMenu;
		
		projectMenu = new ProjectMenu(this, out, in);
		taskMenu = new TaskMenu(this, out, in);
		
		inputHandler = new MenuInputHandler(1, this.out, this.in);
		inputHandler.addAction("Create Project", () -> projectMenu.openCreate());
		inputHandler.addAction("Delete Project", null);
		inputHandler.addAction("Create Tasks (quick create)", () -> taskMenu.openCreateSimple());
		inputHandler.addAction("Create Task (detailed)", null);
		inputHandler.addAction("Save ToDo List in file..", () -> saveCSV());
		inputHandler.addAction("Go back", () -> mainMenu.returnToMe());
	}

	public void open() {
		out.println("ToDo List of: " + Main.activeTodoList.user());
		out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		out.println();
		out.println("[ToDo MainMenu]");
		out.println("What is it that you want to do? To proceed further, enter the action code given inside the [ ]");
		inputHandler.printMenu();
		
		inputHandler.propmtAndHandleInput();
	}
	
	/**
	 * Just a little function that lets the user return to the MainMenu (reopen's it) by
	 * letting the user press any key in order to return to the MainMenu.
	 */
	public void returnToMe() {
		Console.EnterToContinue(out, in);
		Console.clear(out);
		open();
	}
	
	private void saveCSV() {
		// ToDo: Let the function handle the list itself, just need the ToDo-Obejct.
		for (Task element : Main.activeTodoList.tasks()){
			try {
				CSVCreate.writeToDoFile(element, Main.activeTodoList, CSVCreate.getFilePathTodo());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		out.println("Saved ToDo: '" + Main.activeTodoList.user() + "' successfully.");
	}
}
