package hwr.oop.todo;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ManageToDoMenu {
	private PrintStream out = null;
	private Scanner in = null;
	
	private TodoMainMenu todoMainMenu = null;
	
	public ManageToDoMenu(MainMenu mainMenu, OutputStream out, InputStream in) {
		this.out = new PrintStream(out);
		this.in = new Scanner(in);
		
		todoMainMenu = new TodoMainMenu(mainMenu, out, in);
	}
	
	public void openCreate() {
		Console.clear();
		
		String todoName = promptToDoName();
		
		Main.activeTodo = new ToDo(todoName);
		out.println("Created the ToDo-List: '" + todoName + "' sucessfully.");
		
		todoMainMenu.returnToMe();
	}
	
	private String promptToDoName() {
		out.println("Enter your name / the name of the desired ToDo List.");
		
		String todoName;
		while(true) 
		{
			Console.displayInputIndicator();
			todoName = Console.input.nextLine();
			
			if(todoName.isEmpty() == false)
			{
				return todoName;
			}
			else
			{
				out.println("Invalid name (empty). Please enter a valid name!");
			}
		}
	}
	
	public void openLoad() {
		Console.clear();
		
		out.println("To load a ToDo list:");
		String todoName = promptToDoName();
		
		// Niklas: load function call with todoName which serves as the 'user' and get a ToDo object to override Main.activeToDo.
		
		
	}
		
	public void openRemove() {
		
	}
}
