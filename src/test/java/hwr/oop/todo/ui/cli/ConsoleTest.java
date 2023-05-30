package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConsoleTest {
	
	@Test
	@DisplayName("Test if the console is cleared as specified")
	void Test_Clear() {
		OutputStream out = new ByteArrayOutputStream();
		
		Console.clear(new PrintStream(out));
		
		int linebreakCount = CTestHelper.countOccurrences(out.toString(), System.lineSeparator());
		Assertions.assertThat(linebreakCount).isEqualTo(Console.clearScreenLinebreakCount);
	}
	
	@Test
	void Test_EnterToContinue() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("\n");
		InputHandler inputHandler = new InputHandler(new Scanner(in), -1);
		
		boolean fncResult = Console.EnterToContinue(new PrintStream(out), inputHandler);
		
		Assertions.assertThat(out.toString()).isEqualTo(Console.enterToContinueMessage);
		Assertions.assertThat(fncResult).isEqualTo(true);
	}
	
	@Test
	void Test_EnterToContinue_Fails() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);	//no input is allowed
		
		
		boolean fncResult = Console.EnterToContinue(new PrintStream(out), inputHandler);	// not allowed input, should return false
		
		Assertions.assertThat(fncResult).isEqualTo(false);
	}
	
	@Test
	void Test_UserInputIndicator() {
		OutputStream out = new ByteArrayOutputStream();
		
		Console.displayInputIndicator(new PrintStream(out));
		
		Assertions.assertThat(out.toString()).isEqualTo(Console.displayInputIndicatorStr);
	}
	
	@Test
	void Test_ConsoleInstantiate_ToCalmDown_MutationTester()
	{
		Console c = new Console();
	}
}
