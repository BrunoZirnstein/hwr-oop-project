package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuActionHandlerTest {
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2})
	@DisplayName("Test if MenuInputHandler display input options correctly")
	void Test_DisplayInputOptions(int startIndex) {
		OutputStream out = new ByteArrayOutputStream();
		
		MenuActionHandler menuInputHandler = new MenuActionHandler(startIndex, new PrintStream(out), null);
		
		String[] actions = {"Act1", "Act2"};
		
		for(int i=0; i<actions.length; i++) {
			menuInputHandler.addAction(actions[i], null);
		}
		
		menuInputHandler.printMenu();
		
		for(int i=0; i<actions.length; i++) {
			Assertions.assertThat(out.toString()).contains("[" + (i+startIndex) + "] " + actions[i]);
		}
	}
	
	public String actionResult;
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	@DisplayName("Test if MenuInputHandler executes the correct actions")
	void Test_InputCorrectlyResolved(int inputNum) {
		OutputStream out = new ByteArrayOutputStream();								 // last input to quit test for wrong-input test
		InputStream in = CTestHelper.createInputStreamForInput(inputNum + "\n" + "0\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), -1);
		
		String expectedResult[] = { "0 input executed!", "1 input executed", "2 input executed" };
		
		// execute what is being tested
		MenuActionHandler menuInputHandler = new MenuActionHandler(0, new PrintStream(out), inputHandler);
		menuInputHandler.addAction("bla-0", () -> actionResult = expectedResult[0]);
		menuInputHandler.addAction("bla-1", () -> actionResult = expectedResult[1]);
		menuInputHandler.addAction("bla-2", () -> actionResult = expectedResult[2]);
		menuInputHandler.propmtAndHandleInput();
		
		if(inputNum >= 0 && inputNum <= 2) {
			// valid InputID must result in given action!
			Assertions.assertThat(actionResult.toString()).isEqualTo(expectedResult[inputNum]);
			Assertions.assertThat(out.toString()).containsOnlyOnce(Console.displayInputIndicatorStr);
		} else {
			// invalid InputID must result in error message and prompt to try again!
			Assertions.assertThat(out.toString()).contains(menuInputHandler.wrongInputIDMessage);

			// the user input Indicator must be there twice, if one input failed!
			int inputPromptCount = CTestHelper.countOccurrences(out.toString(), Console.displayInputIndicatorStr);
			Assertions.assertThat(inputPromptCount).isEqualTo(2);
		}
	}
	
	@Test
	@DisplayName("Test if MenuInputHandler handles incorrect input format correctly")
	void Test_IncorrectInputFormat_CorrectHandled()
	{
		OutputStream out = new ByteArrayOutputStream();								 // last input to quit test for wrong-input test
		InputStream in = CTestHelper.createInputStreamForInput("arsch\n0\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), -1);
		
		MenuActionHandler menuInputHandler = new MenuActionHandler(0, new PrintStream(out), inputHandler);
		menuInputHandler.addAction("bla", () -> actionResult = "bla");
		menuInputHandler.propmtAndHandleInput();
		
		Assertions.assertThat(out.toString()).contains(menuInputHandler.invalidInputMessage);
	}
}
