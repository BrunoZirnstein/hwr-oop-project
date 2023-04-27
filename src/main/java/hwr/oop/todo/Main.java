package hwr.oop.todo;

import java.io.FileNotFoundException;

public class Main {

	public static ToDo activeTodo = null;
	
	// I love comments :P
	public static void main(String[] args) throws FileNotFoundException {
		MainMenu.open();
	}

}

/*
 * ToDo: Nutzer
	  - Projekt A
	    -> Task 1
	  - Projekt
	    -> Task 1.1
	
	Aber im Code
		Todo -> Task ; Projekt
		Task -> Projekt
 */