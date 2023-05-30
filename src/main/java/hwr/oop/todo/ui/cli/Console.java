package hwr.oop.todo.ui.cli;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
	public final static String enterToContinueMessage = "Press [ENTER] to continue...";
	public final static String displayInputIndicatorStr = "> ";
	public static int clearScreenLinebreakCount = 30;
	
	public static void clear(PrintStream out) {
		out.print(System.lineSeparator().repeat(clearScreenLinebreakCount));
	}
	
	public static void displayInputIndicator(PrintStream out) {
		out.print(displayInputIndicatorStr);
	}
	
	/**
	 * Let's the user press ENTER and tells the user that ENTER should be pressed
	 * @param out 
	 * @param in
	 * @return [true] if everything worked or [false] if no Input could be handled, because the maximum amount of inputs was reached
	 */
	public static boolean EnterToContinue(PrintStream out, InputHandler in)  {
		out.print(enterToContinueMessage);
		String input = in.nextLine();
		
		if(input == null) {
			return false;
		} else {
			return true;
		}
	}
}