package hwr.oop.todo.ui.cli.menus;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

import java.io.PrintStream;
import java.util.List;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.*;

public class DisplayTaskMenu extends InputOptionsMenu {

    public final MenuActionHandler actionHandler;
    public final String[] menuHeadline = {"Display Task Menu",
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
            ""};

    public DisplayTaskMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
        this.out = out;
        this.in = in;

        actionHandler = new MenuActionHandler(1, out, in);
        actionHandler.addAction("Display all tasks", this::displayAllTasks);
        actionHandler.addAction("Display tasks by project", null);
        actionHandler.addAction("Display tasks by tag", null);
        actionHandler.addAction("Display tasks by title", null);
        actionHandler.addAction("Go back..", parentMenu::returnToMe);
    }

    @Override
    public void open() {
        printMenu(out, actionHandler, menuHeadline, null);
    }

    public static final String DISPLAY_TASK_NONE = "{none}";
    public static final String DISPLAY_ALL_TASKS_MSG = "Listing of all existing tasks:" + System.lineSeparator();
    private void displayAllTasks() {
    	Console.clear(out);
    	out.println(DISPLAY_ALL_TASKS_MSG);
    	
    	AsciiTable table = new AsciiTable();
    	table.addRule();
    	table.addRow("Project", "Priority", "Title", "Description", "Status", "Deadline");
    	table.addRule();
    	table.addRule();
    	
    	List<Task> taskList = Main.activeTodo().tasks();
    	for(Task t : taskList) {
    		
    		table.addRow(t.projectName().orElse(DISPLAY_TASK_NONE), t.priority().toString(), t.title(), t.description(), t.status().toString(), t.deadline().toString());
    		table.addRule();
    	}
    	
    	// set table format
    	CWC_LongestLine cwc = new CWC_LongestLine();
    	table.getRenderer().setCWC(cwc);
    	cwc.add(10, 30).add(6, 0).add(10, 30).add(30, 80).add(11, 0).add(10, 0);	// Specify column width
    	
    	//print table
    	out.println(table.render());
    	
    	returnToMe();
    }
 }
