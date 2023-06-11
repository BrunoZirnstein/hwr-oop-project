package hwr.oop.todo.ui.cli.menus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskPriority;
import hwr.oop.todo.core.TaskStatus;
import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.CTestHelper;
import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.menus.MenuTestHelper.EmptyMenu;

public class MainMenuTest {
	
	public static String getMainMenuHeadline() {
		MainMenu mainMenu = new MainMenu(null, null, new EmptyMenu());
		String headline = String.join(System.lineSeparator(), mainMenu.menuHeadline);
		
		if (Main.activeTodo().owner().isPresent()) {
			headline = String.format(headline, Main.activeTodo().owner().get());
		}
		return headline;
	}
	
	@Test
	void Test_open() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		Main.changeActiveTodo(new ToDoList("GLaDOS"));
		
		MainMenu mainMenu = new MainMenu(new PrintStream(out), inputHandler, new EmptyMenu());
		mainMenu.open();
		
		// just an assertion to cool down PIT-Mutation test a little bit - this is actually implementation detail
		int expectedActionCount = 9;
		assertThat(mainMenu.actionHandler.getCount()).isEqualTo(expectedActionCount);
		
		// check if mainMenu is displayed
		assertThat(out.toString()).contains(getMainMenuHeadline());
		assertThat(out.toString()).contains(mainMenu.actionHandler.getMenuPrintString());
	}
	
	@Test
	void Test_open_withEmptyTodoOwner() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		Main.changeActiveTodo(new ToDoList((String)null));
		
		MainMenu mainMenu = new MainMenu(new PrintStream(out), inputHandler, new EmptyMenu());
		mainMenu.open();
		
		// check if mainMenu headline contains the '%s' formatter because no Main.activeToDo exists
		assertThat(out.toString()).contains(getMainMenuHeadline());
		assertThat(out.toString()).contains("%s");
	}
	
	@Test
	void Test_createSimpleTask()  {
		String userInputTaskName = "Exmatrikulieren";
		
		OutputStream out = new ByteArrayOutputStream();					// [1] leads to createSimpleTask method
		InputStream inputStream = CTestHelper.createInputStreamForInput("1\n" + userInputTaskName + "\n");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 2);
		
		Main.changeActiveTodo(new ToDoList("GLaDOS"));
		
		MainMenu mainMenu = new MainMenu(new PrintStream(out), inputHandler, new EmptyMenu());
		mainMenu.open();

		// check if task was created by function
		assertThatNoException().isThrownBy(() -> Main.activeTodo().taskByTitle(userInputTaskName));
		
		// check if function displayed everything
		assertThat(out.toString()).contains(MainMenu.CREATE_SIMPLE_TASK_MSG);
		assertThat(out.toString()).contains(String.format(MainMenu.CREATE_SIMPLE_TASK_SUCCESS_MSG, ""));
		
		// check if function returned to main menu again
		assertThat(out.toString()).contains(Console.ENTER_TO_CONTINUE_MESSAGE);
	}
	
	@Test
	void Test_createTask()  {
		String userInputTaskName = "Exmatrikulieren";
		String userInputTaskDescr = "Das Studium ist zu hart..";
		LocalDate userInputDeadline = LocalDate.parse("2023-06-20");
		TaskPriority userInputPriority = TaskPriority.HIGH;
		TaskStatus userInputStatus = TaskStatus.TODO;
		
		OutputStream out = new ByteArrayOutputStream();					// [2] leads to createTask method
		InputStream inputStream = CTestHelper.createInputStreamForInput("2\n" + userInputTaskName + "\n"
																			  + userInputTaskDescr + "\n"
																			  + userInputDeadline.toString() + "\n"
																			  + userInputPriority.toString() + "\n"
																			  + userInputStatus.toString() + "\n");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 6);
		
		Main.changeActiveTodo(new ToDoList("GLaDOS"));
		
		MainMenu mainMenu = new MainMenu(new PrintStream(out), inputHandler, new EmptyMenu());
		mainMenu.open();
		
		// check if everything is correctly displayed
		assertThat(out.toString()).contains(MainMenu.CREATE_SIMPLE_TASK_MSG);
		assertThat(out.toString()).contains(MainMenu.CREATE_TASK_DESCR_MSG);
		assertThat(out.toString()).contains(MainMenu.CREATE_TASK_DATE_MSG);
		assertThat(out.toString()).contains(String.format(MainMenu.CREATE_TASK_STATUS_MSG_PREFIX, ""));
		assertThat(out.toString()).contains(String.format(MainMenu.CREATE_TASK_STATUS_MSG_PREFIX, ""));
		assertThat(out.toString()).contains(String.format(MainMenu.CREATE_SIMPLE_TASK_SUCCESS_MSG, ""));
		assertThat(out.toString()).contains(Console.ENTER_TO_CONTINUE_MESSAGE);
		
		// check if task was created
		assertThatNoException().isThrownBy(() -> Main.activeTodo().taskByTitle(userInputTaskName));
		
		// check if created task is equal to what the user entered
		Task createdTask = Main.activeTodo().taskByTitle(userInputTaskName);
		assertThat(createdTask.description()).isEqualTo(userInputTaskDescr);
		assertThat(createdTask.deadline()).isEqualTo(userInputDeadline);
		assertThat(createdTask.priority()).isEqualTo(userInputPriority);
		assertThat(createdTask.status()).isEqualTo(userInputStatus);
	}
}
