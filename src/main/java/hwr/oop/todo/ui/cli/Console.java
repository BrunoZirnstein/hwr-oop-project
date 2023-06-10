package hwr.oop.todo.ui.cli;

import java.io.PrintStream;
import java.lang.Enum;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Console {
    public static final String ENTER_TO_CONTINUE_MESSAGE = "Press [ENTER] to continue...";
    public static final String DISPLAY_INPUT_INDICATOR_STR = "> ";
    public static final int CLEAR_SCREEN_LINEBREAK_COUNT = 30;

    private Console() {
        // nothing to do
    }

    public static void clear(PrintStream out) {
        out.print(System.lineSeparator().repeat(CLEAR_SCREEN_LINEBREAK_COUNT));
    }

    public static void displayInputIndicator(PrintStream out) {
        out.print(DISPLAY_INPUT_INDICATOR_STR);
    }

    /**
     * Lets the user press ENTER and tells the user that ENTER should be pressed
     *
     * @return [true] if everything worked or [false] if no Input could be handled, because the maximum amount of inputs was reached
     */
    public static boolean enterToContinue(PrintStream out, InputHandler in) {
        out.print(ENTER_TO_CONTINUE_MESSAGE);
        String input = in.nextLine();

        return input != null;
    }

    /**
     * Prompts the user to enter some string in the console
     *
     * @param out                    PrintStream where the prompt should be displayed
     * @param in                     InputHandler to be able to limit console input-command accesses.
     * @param clearConsoleBeforehand [true]: Clear the console before requesting input from the user [false]: don't clear the console
     * @param displayMsg             Message to display before the input is taken, to let the user know what should be done
     * @param emptyInputErrorMessage If not set to null, the method will not accept empty string input and display's the specified string when
     *                               the user has entered an empty string. In addition, when not set to null, the method will repeat the input request until a non-empty string was entered.
     * @return The string the user has entered or null, if the 'InputHandler in' couldn't read from the console, because the read-console capacity was exceeded.
     */
    public static String promptForString(PrintStream out, InputHandler in, boolean clearConsoleBeforehand, String displayMsg, String emptyInputErrorMessage) {
        if (clearConsoleBeforehand) {
            clear(out);
        }

        out.println(displayMsg);

        while (true) {
            displayInputIndicator(out);
            String userInput = in.nextLine();

            if (userInput == null) {
                return null;
            }

            if (emptyInputErrorMessage != null && userInput.isEmpty()) {
                out.println(emptyInputErrorMessage);
                continue;
            }

            return userInput;
        }
    }
    
    public static final String PROMPT_FOR_DATE_CONVERSION_ERROR_MSG = "Invalid Date input! Please enter the date in the format YYYY-MM-DD.";
    /**
     * Prompts the user to enter a date in the console
     * @param out						PrintStream where the prompt should be displayed
     * @param in						InputHandler to be able to limit console input-command accesses.
     * @param clearConsoleBeforehand	[true]: Clear the console before requesting input from the user [false]: don't clear the console
     * @param displayMsg				Message to display before the input is taken, to let the user know what should be done
     * @param invalidInputErrorMsg		Message that is displayed if the input could not be converted into LocalDate. 
     * 									If set to null, empty input will be accepted which results in returning null - but a conversion error
     * 									message will be still displayed if the user entered unconvertible gibberish.
     * @return The LocalDate the user has entered, or null if the 'InputHandler in' couldn't read from the console, because the read-console capacity was exceeded or if the user entered an empty string.
     */
    public static LocalDate promptForDate(PrintStream out, InputHandler in, boolean clearConsoleBeforehand, String displayMsg, String invalidInputErrorMsg) {
    	if(clearConsoleBeforehand) {
    		clear(out);
    	}
    	
    	out.println(displayMsg);
    	
    	while(true) {
    		displayInputIndicator(out);
    		
    		String userInput = in.nextLine();
    		
    		if(userInput == null) {
    			return null;
    		}
    		
    		if(userInput.isEmpty() && invalidInputErrorMsg == null) {
    			return null;
    		}
    		
    		try {
    			return LocalDate.parse(userInput);
    		}
    		catch (DateTimeException ex) {
    			if(invalidInputErrorMsg != null) {
    				out.println(invalidInputErrorMsg);
    			}
    			else {
    				out.println(PROMPT_FOR_DATE_CONVERSION_ERROR_MSG);
    			}
    		}
    	}
    }
    
    /**
     * Prompts the user to enter a string / enumeration.
     * @param <E> 						Enumeration class to which the input should be converted to
     * @param enumClass 				Enumeration class to which the input should be converted to
     * @param out 						PrintStream where the prompt should be displayed
     * @param in 						InputHandler to be able to limit console input-command accesses.
     * @param clearConsoleBeforehand 	[true]: Clear the console before requesting input from the user [false]: don't clear the console
     * @param allowEmptyInput 			[true]: allow that the user enters nothing so that null is returned or [false]: the user is
     * 						  			treated as long as he entered a valid enumeration.
     * @param displayMsg				Message to display before the input is taken, to let the user know what should be done
     * @param invalidInputMsg			Message that is displayed if the input could not be converted into the given Enumeration class value.
     * @return the converted enumeration value the user entered or null if the user entered nothing or if the user input was killed
     */
    public static <E extends Enum<E>> E promptForEnum(Class<E> enumClass, PrintStream out, InputHandler in, boolean clearConsoleBeforehand, boolean allowEmptyInput, String displayMsg, String invalidInputMsg) {
		if(clearConsoleBeforehand) {
			clear(out);
		}
		out.println(displayMsg);
    	
    	while(true) {
    		displayInputIndicator(out);
    		
			String userInput = in.nextLine();
			
			if(userInput == null) {
				return null;
			}
			
			if(userInput.isEmpty() && 
					allowEmptyInput) {
				return null;
			}
			
			try {
				return Enum.valueOf(enumClass, userInput);
			}
			catch(IllegalArgumentException ex) {
				out.println(invalidInputMsg);
			}
		}
    }
}