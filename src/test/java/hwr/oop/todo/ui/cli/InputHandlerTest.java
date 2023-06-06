package hwr.oop.todo.ui.cli;

import java.io.InputStream;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputHandlerTest {
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 5, 10})
	void Test_next(int maxInputs) {
		
		// Create user input
		String[] userInputs = new String[maxInputs];
		for(int i=0; i<maxInputs; i++) {
			userInputs[i] = Integer.toString(i);
		}
		
		InputStream in = CTestHelper.createInputStreamForInput(String.join("\n", userInputs));
		InputHandler inputHandler = new InputHandler(new Scanner(in), maxInputs);	// only allow one single 
		String result;
		
		for(int i=0; i<maxInputs; i++) {
			
			// test if all inputs are retrieved correctly
			result = inputHandler.next();
			Assertions.assertThat(result).isEqualTo(userInputs[i]);
		}
		
		// test if null is retrieved when no input can be retrieved anymore
		result = inputHandler.next();
		Assertions.assertThat(result).isNull();
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 5, 10})
	void Test_nextLine(int maxInputs) {
		
		// Create user input
		String[] userInputs = new String[maxInputs];
		for(int i=0; i<maxInputs; i++) {
			userInputs[i] = Integer.toString(i);
		}
		
		InputStream in = CTestHelper.createInputStreamForInput(String.join("\n", userInputs));
		InputHandler inputHandler = new InputHandler(new Scanner(in), maxInputs);	// only allow one single 
		String result;
		
		for(int i=0; i<maxInputs; i++) {
			
			// test if all inputs are retrieved correctly
			result = inputHandler.nextLine();
			Assertions.assertThat(result).isEqualTo(userInputs[i]);
		}
		
		// test if null is retrieved when no input can be retrieved anymore
		result = inputHandler.nextLine();
		Assertions.assertThat(result).isNull();
	}
}
