package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;

class ProjectMenuTest {
	
	@Test
	void Test_openCreate() {
		Main.activeTodo = new ToDoList("Bernd das Brot");
		String userInput_projectName = "Portal 3";
		String userInput_projectDeadline = "2023-09-18";
		
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput(userInput_projectName + "\n" + userInput_projectDeadline + "\n\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 3);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		ProjectMenu menu = new ProjectMenu(todoMainMenu, out, inputHandler);
		
		menu.openCreate();
		
		// Check if success-message was printed
		Assertions.assertThat(out.toString()).contains(menu.openCreateSuccessMsg);
		
		// Check if the method returned to the TodoMainMenu
		Assertions.assertThat(out.toString()).contains(TodoMainMenuTest.getMenuHeadline(todoMainMenu));
		
		// Check if method created the project
		Assertions.assertThatNoException().isThrownBy(() -> Main.activeTodo.projectByName(userInput_projectName));
		
		// Check if both input getting private functions displayed their input indicators
		long inputIndicatorCount = out.toString().chars().filter(ch -> ch == Console.DISPLAY_INPUT_INDICATOR_STR.charAt(0)).count();
		Assertions.assertThat(inputIndicatorCount).isEqualTo(3);	// 3 because of the next menu
		
		// Check if the method created the project with the deadline the user specified
		Project project = Main.activeTodo.projectByName(userInput_projectName);
		Assertions.assertThat(project.deadline()).isEqualTo(LocalDate.parse(userInput_projectDeadline));
	}
	
	@Test
	void Test_openCreate_inputKilled() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("");
		InputHandler inputHandler = new InputHandler(new Scanner(in), 0);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		ProjectMenu menu = new ProjectMenu(todoMainMenu, out, inputHandler);
		
		menu.openCreate();	
		
		// Check if the console was cleared (this can only be tested mutation-killing when testing isolated (input-killing)
		Assertions.assertThat(ConsoleTest.isClearInOutput(out.toString())).isTrue();
		
		// because the input was killed for both inputs, the method was shutdown, without creating any project.
		Assertions.assertThat(out.toString()).doesNotContain(menu.openCreateSuccessMsg);
	}
	
	@Test
	void Test_promptProjectNameInput() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("\n");	// invalid input
		InputHandler inputHandler = new InputHandler(new Scanner(in), 1);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		ProjectMenu menu = new ProjectMenu(todoMainMenu, out, inputHandler);
		
		menu.openCreate();
		
		// Check if method tells the user what to do
		Assertions.assertThat(out.toString()).contains(ProjectMenu.PROMPT_PROJECT_NAME_INPUT_MSG);
		
		Assertions.assertThat(out.toString()).contains(Console.DISPLAY_INPUT_INDICATOR_STR);
		
		// Check if function tells the user about the invalid (empty) input
		Assertions.assertThat(out.toString()).contains(ProjectMenu.PROMPT_PROJECT_NAME_INPUT_INVALID_INPUT_MSG);
	}
	
	@Test
	void Test_promptDeadlineInput() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("bla\n12-3\n");	// invalid input for promptDeadlineInput call
		InputHandler inputHandler = new InputHandler(new Scanner(in), 2);
		
		MainMenu mainMenu = new MainMenu(out, inputHandler);
		TodoMainMenu todoMainMenu = new TodoMainMenu(mainMenu, out, inputHandler);
		ProjectMenu menu = new ProjectMenu(todoMainMenu, out, inputHandler);
		
		menu.openCreate();
		
		Assertions.assertThat(out.toString()).contains(ProjectMenu.PROMPT_DEADLINE_INPUT_MSG);
		
		// Test if invalid input leads to the message that tells that the user
		Assertions.assertThat(out.toString()).contains(ProjectMenu.PROMPT_DEADLINE_INPUT_INVALID_INPUT_ERROR_MSG);
	}
}
