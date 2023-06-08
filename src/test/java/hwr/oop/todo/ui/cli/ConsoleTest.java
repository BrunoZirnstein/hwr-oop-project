package hwr.oop.todo.ui.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleTest {

    /**
     * Just checks if the console output was cleared at least one by the Console class
     *
     * @param consoleOutput console output string
     * @return [true] if the console was cleared and [false] if the console was not cleared
     */
    public static boolean isClearInOutput(String consoleOutput) {
        //return consoleOutput.contains(System.lineSeparator().repeat(Console.clearScreenLinebreakCount));
        int linebreakCount = CTestHelper.countOccurrences(consoleOutput, System.lineSeparator());

        return linebreakCount >= Console.CLEAR_SCREEN_LINEBREAK_COUNT;
    }

    @Test
    @DisplayName("Test if the console is cleared as specified")
    void Test_Clear() {
        OutputStream out = new ByteArrayOutputStream();

        Console.clear(new PrintStream(out));

        assertThat(isClearInOutput(out.toString())).isTrue();
    }

    @Test
    void Test_EnterToContinue() {
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = CTestHelper.createInputStreamForInput("\n");
        InputHandler inputHandler = new InputHandler(new Scanner(in), -1);

        boolean fncResult = Console.enterToContinue(new PrintStream(out), inputHandler);

        assertThat(out).hasToString(Console.ENTER_TO_CONTINUE_MESSAGE);
        assertThat(fncResult).isTrue();
    }

    @Test
    void Test_EnterToContinue_Fails() {
        OutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = CTestHelper.createInputStreamForInput("");
        Scanner in = new Scanner(inputStream);
        InputHandler inputHandler = new InputHandler(in, 0);    //no input is allowed


        boolean fncResult = Console.enterToContinue(new PrintStream(out), inputHandler);    // not allowed input, should return false

        assertThat(fncResult).isFalse();
    }

    @Test
    void Test_UserInputIndicator() {
        OutputStream out = new ByteArrayOutputStream();

        Console.displayInputIndicator(new PrintStream(out));

        assertThat(out).hasToString(Console.DISPLAY_INPUT_INDICATOR_STR);
    }

    @Test
    void Test_promptForString() {
        String validUserInput = "日本語が大好きです。";
        OutputStream out = new ByteArrayOutputStream();
        // first input is empty
        InputStream inputStream = CTestHelper.createInputStreamForInput("\n" + validUserInput + "\n");
        Scanner in = new Scanner(inputStream);
        InputHandler inputHandler = new InputHandler(in, 2);

        String methodPromptMsg = "Enter some string!";
        String invalidInputMsg = "Invalid input bro!";
        String result = Console.promptForString(new PrintStream(out), inputHandler, true, methodPromptMsg, invalidInputMsg);

        // we specified that the console should be cleared, check if that really happened
        assertThat(isClearInOutput(out.toString())).isTrue();

        // check if method display our messages correctly
        assertThat(out.toString()).containsOnlyOnce(methodPromptMsg);
        assertThat(out.toString()).containsOnlyOnce(invalidInputMsg);    // must be there because first we passed an empty input
        assertThat(out.toString()).contains(Console.DISPLAY_INPUT_INDICATOR_STR);

        // check if method returned our valid, non empty input
        assertThat(result).isEqualTo(validUserInput);
    }

    @Test
    void Test_promptForString_noInvalidStringHandling() {
        String validUserInput = "日本語が大好きです。";
        OutputStream out = new ByteArrayOutputStream();
        // first input is empty
        InputStream inputStream = CTestHelper.createInputStreamForInput("\n" + validUserInput + "\n");
        Scanner in = new Scanner(inputStream);
        InputHandler inputHandler = new InputHandler(in, 2);

        String methodPromptMsg = "Enter some string!";                                                      // -> no invalid-input handling
        String result = Console.promptForString(new PrintStream(out), inputHandler, false, methodPromptMsg, null);

        // we specified that the console should not be cleared, check if that really happened
        assertThat(isClearInOutput(out.toString())).isFalse();

        // check if method display our messages correctly
        assertThat(out.toString()).containsOnlyOnce(methodPromptMsg);
        assertThat(out.toString()).contains(Console.DISPLAY_INPUT_INDICATOR_STR);

        // check if method returned our invalid input
        assertThat(result).isEmpty();
    }

    @Test
    void Test_promptForString_inputHandlerAbort() {
        OutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = CTestHelper.createInputStreamForInput("");
        Scanner in = new Scanner(inputStream);
        InputHandler inputHandler = new InputHandler(in, 0); // no input possible

        String result = Console.promptForString(new PrintStream(out), inputHandler, false, "bla", null);

        // our result must be null!
        assertThat(result).isNull();
    }
}
