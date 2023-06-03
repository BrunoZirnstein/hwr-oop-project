package hwr.oop.todo.ui.cli;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
	
	Console(){}
	public final static String ENTER_TO_CONTINUE_MESSAGE = "Press [ENTER] to continue...";
	public final static String DISPLAY_INPUT_INDICATOR_STR = "> ";
	public static int CLEAR_SCREEN_LINEBREAK_COUNT = 30;
	
	public static void clear(PrintStream out) {
		out.print(System.lineSeparator().repeat(CLEAR_SCREEN_LINEBREAK_COUNT));
	}
	
	public static void displayInputIndicator(PrintStream out) {
		out.print(DISPLAY_INPUT_INDICATOR_STR);
	}
	
	/**
	 * Let's the user press ENTER and tells the user that ENTER should be pressed
	 * @param out 
	 * @param in
	 * @return [true] if everything worked or [false] if no Input could be handled, because the maximum amount of inputs was reached
	 */
	public static boolean enterToContinue(PrintStream out, InputHandler in)  {
		out.print(ENTER_TO_CONTINUE_MESSAGE);
		String input = in.nextLine();
		
		if(input == null) {
			return false;
		} else {
			return true;
		}
	}
}