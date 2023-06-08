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

class DisplayTaskMenuTest {
	public String getMainMenuHeadline(DisplayTaskMenu menu) {
		String headline = String.join(System.lineSeparator(), menu.menuHeadline);

		if (Main.activeTodo().owner().isPresent()) {
			return String.format(headline, Main.activeTodo().owner().get());
		} else {
			return "";
		}
	}
	
	@Test
	void Test_open() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		Main.changeActiveTodo(new ToDoList("GLaDOS"));
		
		DisplayTaskMenu menu = new DisplayTaskMenu(new PrintStream(out), inputHandler, new EmptyMenu());
		menu.open();
		
		// just an assertion to cool down PIT-Mutation test a little bit - this is actually implementation detail
		int expectedActionCount = 5;
		assertThat(menu.actionHandler.getCount()).isEqualTo(expectedActionCount);
		
		// check if mainMenu is displayed
		assertThat(out.toString()).contains(getMainMenuHeadline(menu));
		assertThat(out.toString()).contains(menu.actionHandler.getMenuPrintString());
	}
}
