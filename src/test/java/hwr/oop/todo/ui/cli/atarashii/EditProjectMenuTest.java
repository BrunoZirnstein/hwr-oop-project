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
import hwr.oop.todo.core.Project;

class EditProjectMenuTest {
	public String getMainMenuHeadline(EditProjectMenu menu, Project p) {
		String headline = String.join(System.lineSeparator(), menu.menuHeadline);
		headline = String.format(headline, p.title());
		return headline;
	}
	
	@Test
	void Test_open() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		Main.changeActiveTodo(new ToDoList("GLaDOS"));
		Project p = new Project.Builder("Order 66").build();
		
		EditProjectMenu menu = new EditProjectMenu(new PrintStream(out), inputHandler, new EmptyMenu(), p);
		menu.open();
		
		// just an assertion to cool down PIT-Mutation test a little bit - this is actually implementation detail
		int expectedActionCount = 4;
		assertThat(menu.actionHandler.getCount()).isEqualTo(expectedActionCount);
		
		// check if mainMenu is displayed
		assertThat(out.toString()).contains(getMainMenuHeadline(menu, p));
		assertThat(out.toString()).contains(menu.actionHandler.getMenuPrintString());
		
		// check if printed menu headline contains the project title
		assertThat(out.toString()).contains(p.title());
	}
}
