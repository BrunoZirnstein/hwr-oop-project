package hwr.oop.todo.ui.cli;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuActionHandler {
	public final String invalidInputMessage = "Invalid input. Please try again";
	public final String wrongInputIDMessage = "Invalid ID. Please enter a valid ID!";
	
	private HashMap<Integer, Runnable> actionMap;
	private HashMap<Integer, String> actionDescriptionsMap;
	private PrintStream out = null;
	private InputHandler in = null;
	
	private int currentIndex = 0;
	private int actionStartIndex = 0;
	
	public MenuActionHandler(int startWithActionIndex, PrintStream out, InputHandler in) {
		actionMap = new HashMap<Integer, Runnable>();
		actionDescriptionsMap = new HashMap<Integer, String>();
		currentIndex = startWithActionIndex;
		actionStartIndex = startWithActionIndex;
		
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
				String result = in.nextLine();
				if(result == null) {
					return;
				}
				
				inputID = Integer.parseInt(result);			
			}
			catch(NumberFormatException ex) {
				out.println(invalidInputMessage);
			}
		}
		
		handleInput(inputID);
	}
	
	public void handleInput(int userInput) {
		if(actionMap.containsKey(userInput) == false)
		{
			out.println(wrongInputIDMessage);
			propmtAndHandleInput();
			return;
		}
		
		Runnable action = actionMap.get(userInput);
		action.run();
	}
}
