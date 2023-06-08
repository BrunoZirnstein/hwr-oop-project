package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;

public class TodoMainMenuTest {

	public static String getMenuHeadline(TodoMainMenu menu) {
		String rawHeadline = String.join(System.lineSeparator(), menu.menuHeadline);
		return String.format(rawHeadline, Main.activeTodo.owner());
	}
	
	@Test
	void ToDoMainMenu_Constructor_Test() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 0);
		
		MainMenu_old mainMenu = new MainMenu_old(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		
		// Avoid nonsense mutation test error : Check if menu contains just as much input options as dynamically given in constructor..
		int expectedMenuCount = 6;
		int actualMenuCount = todoMainMenu.inputHandler.getCount();
		String errorMsg = "The TodoMainMenu menu actions count (" + actualMenuCount + ") does not match to the asserted one (" + expectedMenuCount + ").";
		Assertions.assertThat(actualMenuCount).withFailMessage(errorMsg).isEqualTo(expectedMenuCount);
	}
	
	@Test
	void ToDoMainMenu_ConstrcutorException_Test() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 0);
		
		Assertions.assertThatThrownBy(() -> new TodoMainMenu(null, out, inputHandler)).isInstanceOf(NullPointerException.class);
	}
	
	@Test
	void TodoMainMenu_Open_Test() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 0);
		
		String todoListName = "Learn_Japanese";
		Main.activeTodo = new ToDoList(todoListName);
		
		MainMenu_old mainMenu = new MainMenu_old(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		todoMainMenu.open();
		
		// test if menu headline is displayed properly
		Assertions.assertThat(out.toString()).contains(getMenuHeadline(todoMainMenu));
		
		// test if all menu input options were displayed properly
		Assertions.assertThat(out.toString()).contains(todoMainMenu.inputHandler.getMenuPrintString());
		
		// test if input indicator was printed (which means the input will be received)
		Assertions.assertThat(out.toString()).contains(Console.DISPLAY_INPUT_INDICATOR_STR);
	}
	
	@Test
	void TodoMainMenu_SaveCSV_Test() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("5\n");		// input 5 in menu calls private saveCSV function!
		InputHandler inputHandler = new InputHandler(new Scanner(in), 1);
		
		String todoListName = "Learn_Japanese";
		Main.activeTodo = new ToDoList(todoListName);
		
		MainMenu_old mainMenu = new MainMenu_old(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		todoMainMenu.open();
		
		Assertions.assertThat(out.toString()).contains(todoMainMenu.csvSavedSucessfully_msg);
	}
	
	@Test
	void TodoMainMenu_returnToMe_Test() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 1);
		
		MainMenu_old mainMenu = new MainMenu_old(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		todoMainMenu.returnToMe();
		
		// Check if the console was cleared
		Assertions.assertThat(out.toString()).contains(System.lineSeparator().repeat(Console.CLEAR_SCREEN_LINEBREAK_COUNT));
		
		// Check if the main menu is displayed again (cheap test, the actual open function
		// of the mainMenu is sufficiently tested in a separate unit test)
		// We just check if the menu headline was printed.
		Assertions.assertThat(out.toString()).contains(getMenuHeadline(todoMainMenu));
		
		
		// Test if the returnToMe function terminates when no input can be retrieved
		inputHandler = new InputHandler(new Scanner(in), 0);
		out = new ByteArrayOutputStream();
		
		todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		mainMenu.returnToMe();
		
		// if the returnToMe function aborted, the menu should not be printed
			Assertions.assertThat(out.toString()).doesNotContain(getMenuHeadline(todoMainMenu));
	}
}
