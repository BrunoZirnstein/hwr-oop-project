package hwr.oop.todo.ui.cli;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuInputHandler {
	public static final String INVALID_INPUT_MESSAGE = "Invalid input. Please try again";
	public static final String WRONG_INPUT_ID_MESSAGE = "Invalid ID. Please enter a valid ID!";
	
	private final HashMap<Integer, Runnable> actionMap;
	private final HashMap<Integer, String> actionDescriptionsMap;
	private PrintStream out = null;
	private Scanner in = null;
	
	private int currentIndex = 0;

	public MenuInputHandler(int startWithActionIndex, PrintStream out, Scanner in) {
		actionMap = new HashMap<>();
		actionDescriptionsMap = new HashMap<>();
		currentIndex = startWithActionIndex;

		this.out = out;
		this.in = in;
	}
	
	public void addAction(String description, Runnable action) {
		actionMap.put(currentIndex, action);
		actionDescriptionsMap.put(currentIndex, description);
		
		currentIndex += 1;
	}
	
	public void printMenu() {
		for(Map.Entry<Integer, String> entry : actionDescriptionsMap.entrySet()) {
			out.println("[" + entry.getKey() + "] " + entry.getValue());
		}
	}
	
	public void propmtAndHandleInput() {
		int inputID = -1;
		while(inputID == -1) {
			Console.displayInputIndicator(out);
			try {
				inputID = Integer.parseInt(in.nextLine());			
			}
			catch(NumberFormatException ex) {
				out.println(INVALID_INPUT_MESSAGE);
			}
		}
		
		handleInput(inputID);
	}
	
	public void handleInput(int userInput) {
		if(!actionMap.containsKey(userInput))
		{
			out.println(WRONG_INPUT_ID_MESSAGE);
			propmtAndHandleInput();
			return;
		}
		
		Runnable action = actionMap.get(userInput);
		action.run();
	}
}
