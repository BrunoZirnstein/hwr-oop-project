package hwr.oop.todo.ui.cli;

import java.util.Scanner;

/**
 * A class to handle console inputs. <br>
 * The only reason this class exists is in order to limit the inputs that 
 * can be retrieved from the console so that unit tests no longer time out.
 * @author thomm
 *
 */
public class InputHandler {
	
	private final int maxInputs;
	private int inputGetCount;
	private final Scanner in;
	
	/**
	 * 
	 * @param in Scanner which handles the input
	 * @param maxInputs Maximum count of how much any input-get method can retrieve input. A value < 0 results in no input limitation
	 */
	public InputHandler(Scanner in, int maxInputs) {
		this.maxInputs = maxInputs;
		this.inputGetCount = 0;
		this.in = in;
	}

	/**
	 * Retrieves if the input method is allowed to be called (according to the internal specified input-method-call maximum)
	 * @return [true] if the input method can be called or false if otherwise
	 */
	private boolean isInputAllowed() {
		if(inputGetCount < maxInputs || maxInputs < 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String next() {
		String returnValue = null;
		
		if(isInputAllowed()) {
			returnValue = in.next();
			inputGetCount += 1;			
		}
		
		return returnValue;
	}
	
	public String nextLine() {
		String returnValue = null;
		
		if(isInputAllowed()) {
			returnValue = in.nextLine();
			inputGetCount += 1;			
		}
		
		return returnValue;
	}
	
}
