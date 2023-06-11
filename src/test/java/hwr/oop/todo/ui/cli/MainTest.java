package hwr.oop.todo.ui.cli;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.menus.StartMenu;
import hwr.oop.todo.ui.cli.menus.StartMenuTest;

class MainTest {

	@SuppressWarnings("static-access")
	@Test
	void Test_Main() {		
		// Change the output stream to a one we can read the content from
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		// Fill the console input with something that immediately stops the application (no endless waiting for console input)
		System.setIn(CTestHelper.createInputStreamForInput("2\n"));
		
		Main mainClass = new Main();
		mainClass.main(null);
		
		// check if the main method really calls the StartMenu.open() method
		StartMenu startMenu = new StartMenu(System.out, new InputHandler(new Scanner(System.in), 0));
		assertThat(out.toString()).contains(StartMenuTest.getMenuHeadline(startMenu));
	}
	
	@Test
	void Test_getActiveTodo() {
		ToDoList todo = new ToDoList("Alan Rickman");
		
		Main.changeActiveTodo(todo);
		
		assertThat(Main.activeTodo()).isEqualTo(todo);
	}
}
