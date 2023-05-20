package hwr.oop.todo;

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
	
	public static void EnterToContinue(PrintStream out, Scanner in)  {
		out.print(enterToContinueMessage);
		in.nextLine();
	}
}