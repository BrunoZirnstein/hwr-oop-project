package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hwr.oop.todo.ui.Main;

class ListMenuTest {
	
	@Test
	void Test_openCreate() {
		String inputTodoName = "bank_robbery";
		
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput(inputTodoName + "\n\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 2);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		ListMenu listMenu = new ListMenu(mainMenu, out, inputHandler);
		listMenu.openCreate();
		
		// test if the function created a to-do list
		Assertions.assertThat(Main.activeTodo).isNotNull();
		
		// test if the created to-do list matches the same name that was entered in the console
		Assertions.assertThat(Main.activeTodo.owner()).contains(inputTodoName);
		
		// test if the menu displayed it's "successfully created" message
		Assertions.assertThat(out.toString()).contains(String.format(ListMenu.TODO_CREATED_SUCCESS_MSG, inputTodoName));
		
		// test if the TodoMainMenu is properly displayed
		Assertions.assertThat(out.toString()).contains(TodoMainMenuTest.getMenuHeadline(todoMainMenu));
	}
	
	@Test
	@DisplayName("Test if the ListMenu.openCreate function behaves as expected when input is cut off")
	void Test_openCreate_InputCut() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 0);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		ListMenu listMenu = new ListMenu(mainMenu, out, inputHandler);
		listMenu.openCreate();
		
		// test if clear function was called -> can only be tested when no input is given
		Assertions.assertThat(ConsoleTest.isClearInOutput(out.toString())).isTrue();
		
		// test if a null-named To-Do list was created
		Assertions.assertThat(Main.activeTodo.owner()).isEmpty();
	}
	
	@Test
	void Test_promptToDoName() {
		String[] userInputs = {"japanese", "", null};
		
		for(String userInput : userInputs) {
			
			OutputStream out = new ByteArrayOutputStream();
			InputStream in = CTestHelper.createInputStreamForInput(userInput + "\n");
			InputHandler inputHandler = new InputHandler(new Scanner(in), 1);
			
			MainMenu mainMenu = new MainMenu(out, inputHandler);
			ListMenu listMenu = new ListMenu(mainMenu, out, inputHandler);
			listMenu.openCreate(); // -> openCreate calls the private promptToDoName function.
			
			String consoleOutput = out.toString();
			
			// test if the output was generated that tells the user what to do
			Assertions.assertThat(consoleOutput).contains(ListMenu.TODO_NAME_INPUT_PROMPT_MSG);
			
			// test if the console user-input indicator is shown
			Assertions.assertThat(consoleOutput).contains(Console.DISPLAY_INPUT_INDICATOR_STR);
			
			// test if the input was handled correctly
			if(userInput != null) {
				if(userInput.isEmpty()) {
					
					// when the input string is empty, a message must appear to notify the user
					Assertions.assertThat(consoleOutput).contains(ListMenu.TO_NAME_PROMPT_EMPTY_INPUT_MSG);
					
					return;
				} 
			}
			
			// otherwise, a To-Do list with the given name must have been created
			Assertions.assertThat(Main.activeTodo.owner()).contains(userInput);
		}
	}
	
	@Test
	void Test_openLoad() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 0);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		ListMenu listMenu = new ListMenu(mainMenu, out, inputHandler);
		listMenu.openLoad();
		
		// test if console was cleared
		Assertions.assertThat(ConsoleTest.isClearInOutput(out.toString())).isTrue();
		
		// test if function tells the user what to do
		Assertions.assertThat(out.toString()).contains(listMenu.OPEN_LOAD_PROMPT_MSG);
	}
	
	@Test
	void Test_openRemove() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 0);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		ListMenu listMenu = new ListMenu(mainMenu, out, inputHandler);
		listMenu.openRemove();
	}
}
