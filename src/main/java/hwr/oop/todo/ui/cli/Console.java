package hwr.oop.todo.ui.cli;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {

	Console(){}
	public static final String ENTERTOCONTINUEMESSAGE = "Press [ENTER] to continue...";
	public static final String DISPLAYINPUTINDICATORSTR = "> ";
	public static final int CLEARSCREENLINEBREAKCOUNT = 30;
	
	public static void clear(PrintStream out) {
		out.print(System.lineSeparator().repeat(CLEARSCREENLINEBREAKCOUNT));
	}
	
	public static void displayInputIndicator(PrintStream out) {
		out.print(DISPLAYINPUTINDICATORSTR);
	}
	
	public static void enterToContinue(PrintStream out, Scanner in)  {
		out.print(ENTERTOCONTINUEMESSAGE);
		in.nextLine();
	}
}