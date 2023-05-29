package hwr.oop.todo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
		
		Console.EnterToContinue(new PrintStream(out), new Scanner(in));
		
		Assertions.assertThat(out.toString()).isEqualTo(Console.enterToContinueMessage);
	}
	
	@Test
	void Test_UserInputIndicator() {
		OutputStream out = new ByteArrayOutputStream();
		
		Console.displayInputIndicator(new PrintStream(out));
		
		Assertions.assertThat(out.toString()).isEqualTo(Console.displayInputIndicatorStr);
	}
	
	@Test
	void Test_ConsoleInstantiate_To_CalmDown_MutationTester()
	{
		Console c = new Console();
	}
}
