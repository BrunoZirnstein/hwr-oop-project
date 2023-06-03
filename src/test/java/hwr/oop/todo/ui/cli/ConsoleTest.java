package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleTest {
	
	/**
	 * Just checks if the console output was cleared at least one by the Console class
	 * @param consoleOutput console output string
	 * @return [true] if the console was cleared and [false] if the console was not cleared
	 */
	public static boolean isClearInOutput(String consoleOutput) {
		//return consoleOutput.contains(System.lineSeparator().repeat(Console.clearScreenLinebreakCount));
		int linebreakCount = CTestHelper.countOccurrences(consoleOutput, System.lineSeparator());
		
		
		if(linebreakCount >= Console.CLEAR_SCREEN_LINEBREAK_COUNT) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Test
	@DisplayName("Test if the console is cleared as specified")
	void Test_Clear() {
		OutputStream out = new ByteArrayOutputStream();
		
		Console.clear(new PrintStream(out));
		
		assertThat(isClearInOutput(out.toString())).isEqualTo(true);
	}
	
	@Test
	void Test_EnterToContinue() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), -1);
		
		boolean fncResult = Console.enterToContinue(new PrintStream(out), inputHandler);
		
		assertThat(out.toString()).isEqualTo(Console.ENTER_TO_CONTINUE_MESSAGE);
		assertThat(fncResult).isEqualTo(true);
	}
	
	@Test
	void Test_EnterToContinue_Fails() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);	//no input is allowed
		
		
		boolean fncResult = Console.enterToContinue(new PrintStream(out), inputHandler);	// not allowed input, should return false
		
		assertThat(fncResult).isEqualTo(false);
	}
	
	@Test
	void Test_UserInputIndicator() {
		OutputStream out = new ByteArrayOutputStream();
		
		Console.displayInputIndicator(new PrintStream(out));
		
		assertThat(out.toString()).isEqualTo(Console.DISPLAY_INPUT_INDICATOR_STR);
	}
	
	@Test
	void Test_ConsoleInstantiate_ToCalmDown_MutationTester()
	{
		Console c = new Console();
		assertThat(c).isNotNull();
	}
}
