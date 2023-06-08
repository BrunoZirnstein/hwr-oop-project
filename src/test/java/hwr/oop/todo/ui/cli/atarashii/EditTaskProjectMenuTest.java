package hwr.oop.todo.ui.cli.atarashii;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.CTestHelper;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.atarashii.MenuTestHelper.EmptyMenu;

public class EditTaskProjectMenuTest {
	public String getMainMenuHeadline(EditTaskProjectMenu menu, Task t) {
		String headline = String.join(System.lineSeparator(), menu.menuHeadline);
		headline = String.format(headline, t.title());
		return headline;
	}
	
	@Test
	void Test_open() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		Main.activeTodo = new ToDoList("GLaDOS");
		Task t = new Task.Builder("Find the meaning of life").build();
		
		EditTaskProjectMenu menu = new EditTaskProjectMenu(new PrintStream(out), inputHandler, new EmptyMenu(), t);
		menu.open();
		
		// just an assertion to cool down PIT-Mutation test a little bit - this is actually implementation detail
		int expectedActionCount = 4;
		assertThat(menu.actionHandler.getCount()).isEqualTo(expectedActionCount);
		
		// check if mainMenu is displayed
		assertThat(out.toString()).contains(getMainMenuHeadline(menu, t));
		assertThat(out.toString()).contains(menu.actionHandler.getMenuPrintString());
	}
}
