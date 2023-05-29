package hwr.oop.todo;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ListMenu {
	private PrintStream out = null;
	private Scanner in = null;
	
	private TodoMainMenu todoMainMenu = null;
	private MainMenu mainMenu;
	
	public ListMenu(MainMenu mainMenu, OutputStream out, InputStream in) {
		this.out = new PrintStream(out);
		this.in = new Scanner(in);
		
		this.mainMenu = mainMenu;
		todoMainMenu = new TodoMainMenu(mainMenu, out, in);
	}
	
	public void openCreate() {
		Console.clear(out);
		
		String todoName = promptToDoName();
		
		Main.activeTodoList = new ToDo(todoName);
		out.println("Created the ToDo-List: '" + todoName + "' sucessfully.");
		
		todoMainMenu.returnToMe();
	}
	
	private String promptToDoName() {
		out.println("Enter your name / the name of the desired ToDo List.");
		
		String todoName;
		while(true) 
		{
			Console.displayInputIndicator(out);
			todoName = in.nextLine();
			
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
		Console.clear(out);
		
		out.println("To load a ToDo list:");
		String todoName = promptToDoName();
		
		// Niklas: load function call with todoName which serves as the 'user' and get a ToDo object to override Main.activeToDo.
		try {
			CSVReader reader = new CSVReader();
			Main.activeTodoList = reader.readToDoFile(todoName);
			todoMainMenu.returnToMe();
		}
		catch (Exception e) {
			out.println("Something horrible happend, the todo list was not found (maybe)");
			mainMenu.returnToMe();
		}
		
	}
		
	public void openRemove() {
		
	}
}
