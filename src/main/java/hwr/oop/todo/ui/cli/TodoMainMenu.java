package hwr.oop.todo.ui.cli;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.core.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TodoMainMenu {
	private PrintStream out = null;
	private InputHandler in = null;

	private MainMenu mainMenu = null;
	private ProjectMenu projectMenu = null;
	private TaskMenu taskMenu = null;

	public final MenuActionHandler inputHandler;
	
	public final String[] menuHeadline = {"ToDo List of: {}", 
									 	  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 
									 	  "",
									 	  "[ToDo MainMenu]",
									 	  "What is it that you want to do? To proceed further, enter the action code given inside the [ ]"};

	public TodoMainMenu(MainMenu mainMenu, OutputStream out, InputHandler in) {
		this.out = new PrintStream(out);
		this.in = in;

		if (mainMenu == null) {
			throw new NullPointerException("the mainMenu parameter is invalid!");
		}
		this.mainMenu = mainMenu;

		projectMenu = new ProjectMenu(this, out, in);
		taskMenu = new TaskMenu(this, out, in);

		inputHandler = new MenuActionHandler(1, this.out, this.in);
		inputHandler.addAction("Create Project", () -> projectMenu.openCreate());
		inputHandler.addAction("Delete Project", null);
		inputHandler.addAction("Create Tasks (quick create)", () -> taskMenu.openCreateSimple());
		inputHandler.addAction("Create Task (detailed)", null);
		inputHandler.addAction("Save ToDo List in file..", () -> saveCSV());
		inputHandler.addAction("Go back", () -> mainMenu.returnToMe());
	}

	public void open() {
		String headline = String.join(System.lineSeparator(), menuHeadline);
		out.println(String.format(headline, Main.activeTodo.owner()));
		inputHandler.printMenu();

		inputHandler.propmtAndHandleInput();
	}

	/**
	 * Just a little function that lets the user return to the MainMenu (reopen's
	 * it) by
	 * letting the user press any key in order to return to the MainMenu.
	 */
	public void returnToMe() {
		if(Console.EnterToContinue(out, in) == false) {
			return;
		}
			
		Console.clear(out);
		open();
	}

	public final String csvSavedSucessfully_msg = "The Todo-List was saved successfully -> ";
	private void saveCSV() {
		// ToDo: Let the function handle the list itself, just need the ToDo-Obejct.
		/*
		 * for (Task element : Main.activeTodo.tasks()){
		 * try {
		 * CSVCreate.writeTaskFile(element, Main.activeTodo,
		 * CSVCreate.getFilePathTodo());
		 * } catch (IOException e) {
		 * throw new RuntimeException(e);
		 * }
		 * }
		 */
		out.println(csvSavedSucessfully_msg + Main.activeTodo.owner());
	}
}
