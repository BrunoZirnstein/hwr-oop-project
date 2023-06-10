package hwr.oop.todo.ui.cli.menus;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import hwr.oop.todo.ui.cli.CTestHelper;
import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.ConsoleTest;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

class InputOptionsMenuTest {
	
	static class myMenu extends InputOptionsMenu {
		public final MenuActionHandler actionHandler;
		
		public myMenu(PrintStream out, InputHandler in) {
			this.out = out;
			this.in = in;
			
			actionHandler = new MenuActionHandler(0, out, in);
			actionHandler.addAction("How Are You Holding Up? Because I’m A Potato.", null);
		}
		
		public String openMsg = "I think we can put our differences behind us... for Science... you Monster.";
		@Override
		public void open() {
			out.println(openMsg);
		}
	}
	
	@Test
	void Test_open() {
		OutputStream out = new ByteArrayOutputStream();
		PrintStream output = new PrintStream(out);
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		String[] headline = {
				"Remember When The Platform Was Sliding Into The Fire Pit, And I Said 'Goodbye,'",
				"And You Were Like 'No Way!'",
				"And Then I Was All, 'We Pretended We Were Going To Murder You.'",
				"That Was Great."
		};
		
		myMenu menu = new myMenu(output, inputHandler);
		myMenu.printMenu(output, menu.actionHandler, headline, null);
		
		// check if menu is printed out correctly
		assertThat(out.toString()).contains(String.join(System.lineSeparator(), headline));
		assertThat(out.toString()).contains(menu.actionHandler.getMenuPrintString());
		assertThat(out.toString()).contains(Console.DISPLAY_INPUT_INDICATOR_STR);
	}

	@Test
	void Test_returnToMe() {
		OutputStream out = new ByteArrayOutputStream();
		PrintStream output = new PrintStream(out);
		InputStream inputStream = CTestHelper.createInputStreamForInput("\n");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 1);
		
		myMenu menu = new myMenu(output, inputHandler);
		menu.returnToMe();
		
		// check if user was informed to press ENTER
		assertThat(out.toString()).contains(Console.ENTER_TO_CONTINUE_MESSAGE);
		
		// check if console was cleared before the new menu is shown
		assertThat(ConsoleTest.isClearInOutput(out.toString())).isTrue();
		
		// Check if menu was opened after pressing ENTER
		assertThat(out.toString()).contains(menu.openMsg);
	}
	
	@Test
	void Test_returnToMe_InputKilled() {
		OutputStream out = new ByteArrayOutputStream();
		PrintStream output = new PrintStream(out);
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		myMenu menu = new myMenu(output, inputHandler);
		menu.returnToMe();
		
		// check if user was informed to press ENTER
		assertThat(out.toString()).contains(Console.ENTER_TO_CONTINUE_MESSAGE);
		
		// check if console was not cleared, because Input was killed
		assertThat(ConsoleTest.isClearInOutput(out.toString())).isFalse();
		
		// Check if menu was not opened, because Input was killed
		assertThat(out.toString()).doesNotContain(menu.openMsg);
	}
}
