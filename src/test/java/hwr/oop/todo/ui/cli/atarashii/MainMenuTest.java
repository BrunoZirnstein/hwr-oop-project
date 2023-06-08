package hwr.oop.todo.ui.cli.atarashii;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.CTestHelper;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.atarashii.MenuTestHelper.EmptyMenu;

public class MainMenuTest {
	
	public static String getMainMenuHeadline() {
		MainMenu mainMenu = new MainMenu(null, null, new EmptyMenu());
		String headline = String.join(System.lineSeparator(), mainMenu.menuHeadline);
		headline = String.format(headline, Main.activeTodo.owner().get());
		return headline;
	}
	
	@Test
	void Test_open() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		Main.activeTodo = new ToDoList("GLaDOS");
		
		MainMenu mainMenu = new MainMenu(new PrintStream(out), inputHandler, new EmptyMenu());
		mainMenu.open();
		
		// just an assertion to cool down PIT-Mutation test a little bit - this is actually implementation detail
		int expectedActionCount = 9;
		assertThat(mainMenu.actionHandler.getCount()).isEqualTo(expectedActionCount);
		
		// check if mainMenu is displayed
		assertThat(out.toString()).contains(getMainMenuHeadline());
		assertThat(out.toString()).contains(mainMenu.actionHandler.getMenuPrintString());
	}
}
