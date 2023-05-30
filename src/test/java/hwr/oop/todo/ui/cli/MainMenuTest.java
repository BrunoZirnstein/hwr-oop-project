package hwr.oop.todo.ui.cli;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainMenuTest {
	
	@Test
	@DisplayName("Open Main Menu Test")
	void testMenuOpen() throws IOException
	{
		InputStream input = CTestHelper.createInputStreamForInput("4\n");	// the open method already expects an input, but we want the function to quit immediately
		OutputStream output = new ByteArrayOutputStream();
		
		MainMenu mainMenu = new MainMenu(output, input);
		mainMenu.open();
		
		Assertions.assertThat(output.toString()).contains(String.join(System.lineSeparator(), mainMenu.menuHeadline));
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
