package hwr.oop.todo.ui.cli.menus;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskPriority;
import hwr.oop.todo.core.TaskStatus;
import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.CTestHelper;
import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.ConsoleTest;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.menus.MenuTestHelper.EmptyMenu;

class DisplayTaskMenuTest {
	public String getMainMenuHeadline(DisplayTaskMenu menu) {
		String headline = String.join(System.lineSeparator(), menu.menuHeadline);

		if (Main.activeTodo().owner().isPresent()) {
			return String.format(headline, Main.activeTodo().owner().get());
		} else {
			return "";
		}
	}
	
	private boolean isTableCorrectlyFormatted(String consoleOutput, int numOfDisplayedEntries) {
		int expectedRules = 1 + 2 + numOfDisplayedEntries;
		int foundRules = StringUtils.countMatches(consoleOutput, "┐") + StringUtils.countMatches(consoleOutput, "┤") + StringUtils.countMatches(consoleOutput, "┘");
		return expectedRules == foundRules;
	}
	
	@Test
	void Test_open() {
		OutputStream out = new ByteArrayOutputStream();
		InputStream inputStream = CTestHelper.createInputStreamForInput("");
		Scanner in = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(in, 0);
		
		Main.changeActiveTodo(new ToDoList("GLaDOS"));
		
		DisplayTaskMenu menu = new DisplayTaskMenu(new PrintStream(out), inputHandler, new EmptyMenu());
		menu.open();
		
		// just an assertion to cool down PIT-Mutation test a little bit - this is actually implementation detail
		int expectedActionCount = 5;
		assertThat(menu.actionHandler.getCount()).isEqualTo(expectedActionCount);
		
		// check if mainMenu is displayed
		assertThat(out.toString()).contains(getMainMenuHeadline(menu));
		assertThat(out.toString()).contains(menu.actionHandler.getMenuPrintString());
	}
	
	@Test
    void Test_displayAllTasks() {
    	OutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = CTestHelper.createInputStreamForInput("1\n");
        Scanner in = new Scanner(inputStream);
        InputHandler inputHandler = new InputHandler(in, 1);     
        
        // create tasks
        Main.changeActiveTodo(new ToDoList("Thomas"));
        Main.activeTodo().addProject(new Project.Builder("Star Wars").build());
        
        Main.activeTodo().addTask(new Task.Builder("Execute Order 66")
				        					.description("Time to be the leader of the universe!")
				        					.deadline(LocalDate.parse("2023-12-12"))
				        					.status(TaskStatus.IN_PROGRESS)
				        					.priority(TaskPriority.HIGH)
				        					.projectName("Star Wars")
				        					.build());
        
        Main.activeTodo().addTask(new Task.Builder("Do sport")
											.description("Go to the gym!")
											.deadline(LocalDate.parse("2023-06-11"))
											.status(TaskStatus.TODO)
											.priority(TaskPriority.MEDIUM)
											.build());
        
        Main.activeTodo().addTask(new Task.Builder("Order from Amazon")
											.description("Order protein powder")
											.deadline(LocalDate.parse("2023-07-05"))
											.status(TaskStatus.IN_PROGRESS)
											.priority(TaskPriority.LOW)
											.build());
        
        // display all our tasks
        DisplayTaskMenu menu = new DisplayTaskMenu(new PrintStream(out), inputHandler, new EmptyMenu());
        menu.open();
        
        // check if method cleared console before printing all tasks and printed message
        assertThat(ConsoleTest.isClearInOutput(out.toString())).isTrue();
        assertThat(out.toString()).contains(DisplayTaskMenu.DISPLAY_ALL_TASKS_MSG);
        
        // check if all our tasks were displayed correctly
        List<Task> tasks = Main.activeTodo().tasks();
        for(Task t : tasks) {
        	assertThat(out.toString()).contains(t.title());
        	assertThat(out.toString()).contains(t.description());
        	assertThat(out.toString()).contains(t.deadline().toString());
        	assertThat(out.toString()).contains(t.status().toString());
        	assertThat(out.toString()).contains(t.priority().toString());
        	assertThat(out.toString()).contains(t.projectName().orElse(DisplayTaskMenu.DISPLAY_TASK_NONE));
        }
        assertThat(isTableCorrectlyFormatted(out.toString(), tasks.size())).isTrue();
        
        // check if method returned back to menu
        assertThat(out.toString()).contains(Console.ENTER_TO_CONTINUE_MESSAGE);
    }
}
