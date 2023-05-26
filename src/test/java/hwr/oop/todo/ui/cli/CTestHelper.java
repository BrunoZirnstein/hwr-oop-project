package hwr.oop.todo.ui.cli;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Disabled;

/**
 * Console Test helper class
 * @author GorgonzolaBYTE
 *
 */
@Disabled
public class CTestHelper {
	
	public static String retrieveResultFrom(OutputStream outputStream) {
        String outputText = outputStream.toString();
        String key = "output:";
        return outputText.substring(outputText.indexOf(key) + key.length()).trim();
    }
	
	public static InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
	
	/**
	 * Counts how many occurrences of sub are contained in the string input
	 * @param input String in which the occurrences of sub are counted
	 * @param sub String sequence that is counted inside of the string input
	 * @return The count of 'sub' in 'input'
	 */
	public static int countOccurrences(CharSequence input, CharSequence sub) {
        int count = 0;
        int lastIndex = 0;

        while (lastIndex != -1) {
            lastIndex = input.toString().indexOf(sub.toString(), lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += sub.length();
            }
        }

        return count;
    }
}
