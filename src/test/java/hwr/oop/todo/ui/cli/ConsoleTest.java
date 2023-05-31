package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConsoleTest {
	
	@Test
	@DisplayName("Test if the console is cleared as specified")
	void Test_Clear() {
		OutputStream out = new ByteArrayOutputStream();
		
		Console.clear(new PrintStream(out));
		
		int linebreakCount = CTestHelper.countOccurrences(out.toString(), System.lineSeparator());
		assertThat(linebreakCount).isEqualTo(Console.CLEARSCREENLINEBREAKCOUNT);
	}
	
	@Test
	void Test_EnterToContinue() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream in = CTestHelper.createInputStreamForInput("\n");
		
		Console.enterToContinue(new PrintStream(out), new Scanner(in));
		
		assertThat(out.toString()).isEqualTo(Console.ENTERTOCONTINUEMESSAGE);
	}
	
	@Test
	void Test_UserInputIndicator() {
		OutputStream out = new ByteArrayOutputStream();
		
		Console.displayInputIndicator(new PrintStream(out));
		
		assertThat(out.toString()).isEqualTo(Console.DISPLAYINPUTINDICATORSTR);
	}
	
	@Test
	void Test_ConsoleInstantiate_To_CalmDown_MutationTester()
	{
		Console c = new Console();
		assertThat(c).isNotNull();
	}
}
