package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;

public class TaskMenuTest {

	@Test
	void Test_openCreateSimple() {
		Main.activeTodo = new ToDoList("Kiba Inuzuka");
		String taskName_userInput = "Exmatrikulieren";
		
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput(taskName_userInput + "\n\n");	// second line break for returnToMe function
		InputHandler inputHandler = new InputHandler(new Scanner(in), 2);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		TaskMenu menu = new TaskMenu(todoMainMenu, out, inputHandler);
		
		
		menu.openCreateSimple();
		
		// Test if the menu cleared the console
		Assertions.assertThat(ConsoleTest.isClearInOutput(out.toString())).isTrue();
		
		// Test if the menu.openCreateSimple(); function really added the task we specified
		Assertions.assertThatNoException().isThrownBy(() -> Main.activeTodo.taskByTitle(taskName_userInput));
		
		// Test if the success-message was displayed
		Assertions.assertThat(out.toString()).contains(String.format(menu.openCreateSimpleSuccess_msg, taskName_userInput));
		
		// Test if function returned to TodoMainMenu
		Assertions.assertThat(out.toString()).contains(TodoMainMenuTest.getMenuHeadline(todoMainMenu));
	}
	
	@Test
	@DisplayName("Test if the openCreateSimple method quits when the input is killed")
	void Test_openCreateSimple_InputKillAbort() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("");	// second line break for returnToMe function
		InputHandler inputHandler = new InputHandler(new Scanner(in), 0);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		TaskMenu menu = new TaskMenu(todoMainMenu, out, inputHandler);
		
		menu.openCreateSimple();
		
		// Test if the menu cleared the console
		Assertions.assertThat(ConsoleTest.isClearInOutput(out.toString())).isTrue();
		
		// When the input was killed, no "successfully created" message can be displayed
		Assertions.assertThat(out.toString()).doesNotContain(menu.openCreateSimpleSuccess_msg);
	}
	
	@Test
	void Test_promptTaskName() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("bla\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 1);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		TaskMenu menu = new TaskMenu(todoMainMenu, out, inputHandler);
		
		menu.openCreateSimple();
		
		// Test if function displays it's prompt
		Assertions.assertThat(out.toString()).contains(menu.promptTaskName_msg);
		
		// Test if functions displays input indicator
		Assertions.assertThat(out.toString()).contains(Console.displayInputIndicatorStr);
	}
	
	@Test
	void Test_promptTaskName_InvalidInput() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 1);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		TaskMenu menu = new TaskMenu(todoMainMenu, out, inputHandler);
		
		menu.openCreateSimple();
		
		// Test if invalid input message is displayed with empty input
		Assertions.assertThat(out.toString()).contains(menu.promptTaskName_invalidNameMsg);
	}
}
