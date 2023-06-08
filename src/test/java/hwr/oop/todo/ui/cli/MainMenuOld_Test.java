package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainMenuOld_Test {
	
	public static String getMenuHeadline(MainMenu_old mainMenu) {
		return String.join(System.lineSeparator(), mainMenu.menuHeadline);
	}
	
	@Test
	@DisplayName("Open Main Menu Test")
	void testMenuOpen() throws IOException {
		InputStream input = CTestHelper.createInputStreamForInput("");
		InputHandler inputHandler = new InputHandler(new Scanner(input), 0);
		OutputStream output = new ByteArrayOutputStream();
		
		MainMenu_old mainMenu = new MainMenu_old(output, inputHandler);
		mainMenu.open();
		
		
		// Check if MainMenu display it's headline
		Assertions.assertThat(output.toString()).contains(getMenuHeadline(mainMenu));
		
		// Check if MainMenu display it's user input options
		Assertions.assertThat(output.toString()).contains(mainMenu.inputHandler.getMenuPrintString());
		
		// Check if MainMenu asks for input
		Assertions.assertThat(output.toString()).contains(Console.DISPLAY_INPUT_INDICATOR_STR);
	}

	@Test
	void Test_ReturnToMe() {
		InputStream input = CTestHelper.createInputStreamForInput("\n");
		InputHandler inputHandler = new InputHandler(new Scanner(input), 1);
		OutputStream output = new ByteArrayOutputStream();
		
		MainMenu_old mainMenu = new MainMenu_old(output, inputHandler);
		mainMenu.returnToMe();
		
		// Check if the console was cleared
		Assertions.assertThat(output.toString()).contains(System.lineSeparator().repeat(Console.CLEAR_SCREEN_LINEBREAK_COUNT));
		
		// Check if the main menu is displayed again (cheap test, the actual open function
		// of the mainMenu is sufficiently tested in a separate unit test)
		// We just check if the menu headline was printed.
		Assertions.assertThat(output.toString()).contains(String.join(System.lineSeparator(), mainMenu.menuHeadline));
		
		
		// Test if the returnToMe function terminates when no input can be retrieved
		inputHandler = new InputHandler(new Scanner(input), 0);
		output = new ByteArrayOutputStream();
		
		mainMenu = new MainMenu_old(output, inputHandler);
		mainMenu.returnToMe();
		
		// if the returnToMe function aborted, the menu should not be printed
		Assertions.assertThat(output.toString()).doesNotContain(String.join(System.lineSeparator(), mainMenu.menuHeadline));
	}
	
	@Test
	void Test_MainMenuConstructor() {
		InputStream input = CTestHelper.createInputStreamForInput("\n");
		InputHandler inputHandler = new InputHandler(new Scanner(input), 1);
		OutputStream output = new ByteArrayOutputStream();
		
		MainMenu_old mainMenu = new MainMenu_old(output, inputHandler);
		
		// Check if MainMenu displays all input
		int mainMenuOptions = mainMenu.inputHandler.getCount();
		int expectedMenuOptions = 4;
		String errorMessage = "The expected Menu Input options of " + expectedMenuOptions + " was instead " + mainMenuOptions + ". The MainMenu's constructor might changed the options (MenuActionHandler)";
		Assertions.assertThat(mainMenuOptions).withFailMessage(errorMessage).isEqualTo(expectedMenuOptions);
	}
    
	/*
    private void createConsoleInput()
	{
    	
    }
    */
    //bla 
    
    /*
    -> Zum Testen von Konsolen In-/Output: Eigenen Print & Scan Input erstellen.
    -> Also lieber eigene Input-Streams machen und nicht die System.bla sache nutzen
    -> Am besten: State-Pattern für diese Problematik
       
    class ExampleConsoleUserInterfaceTest {

    @Test
    void consoleUI_TypeThreeAndFour_OutputIsSeven() {
        // given
        InputStream inputStream = createInputStreamForInput("3\n4\n"); // -> Das ist der Input, von eigenem Input dingens
        OutputStream outputStream = new ByteArrayOutputStream();
        ConsoleUserInterface consoleUI = new ConsoleUserInterface(outputStream, inputStream);
        // when
        consoleUI.start();
        // then
        String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("7");
    }

    @Test
    @Disabled("Disabled, because this is a manual test")
    void manualTest() {
        ConsoleUserInterface ui = new ConsoleUserInterface(System.out, System.in);
        ui.start();
    }

    private String retrieveResultFrom(OutputStream outputStream) {
        String outputText = outputStream.toString();
        String key = "output:";
        return outputText.substring(outputText.indexOf(key) + key.length()).trim();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    // Just as a static class, to have a gist one pager.
    // This is SUT. Thus, it should be under src/main!
    static class ConsoleUserInterface {

        private final PrintStream out;
        private final Scanner in;

        public ConsoleUserInterface(OutputStream out, InputStream in) {
            this.out = new PrintStream(out);
            this.in = new Scanner(in);
        }

        public void start() {
            out.println("Enter number 1");
            int x = in.nextInt();
            out.println("Enter number 2");
            int y = in.nextInt();
            out.println("output: " + (x + y));
        }
    }
}
     */
}
