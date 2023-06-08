package hwr.oop.todo.ui.cli.atarashii;

import java.io.PrintStream;

import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

/*
 * Class for Menu-Classes which serves as a main-menu, 
 * Menus that just let's the user enter numbers to do something
 */
public abstract class InputOptionsMenu {
	protected PrintStream out;
	protected InputHandler in;
	
	public abstract void open();
	
	/**
	 * A shortcut to print the menu with headline and input options, as well as handle the user input.
	 * @param out 
	 * @param actionHandler MenuActionHandler which handles the input and prints the available input options.
	 * @param headline Headline to be printed for the menu
	 * @param insertHeadlineValue If the headline contains a placeholder, this String will be inserted. If null is passed, nothing is inserted.
	 */
	public static void printMenu(PrintStream out, MenuActionHandler actionHandler, String[] headline, String insertHeadlineValue) {
		String print_headline = String.join(System.lineSeparator(), headline); 
		
		if(insertHeadlineValue != null) {
			print_headline = String.format(print_headline, insertHeadlineValue);
		}
		
		//out.println(String.format(todoCreatedSucess_msg, todoName));
		
		out.println(print_headline);
		
		actionHandler.printMenu();
		
		actionHandler.propmtAndHandleInput();
	}
	
	
	/**
	 * Let's the user press ENTER, clears the console and opens the menu.
	 * This method is specifically designed for sub-menus, which will return the super-menu.
	 */
	public void returnToMe() {
		if(Console.enterToContinue(out, in) == false) {
			return;
		}
		
		Console.clear(out);
		open();
	}
}
