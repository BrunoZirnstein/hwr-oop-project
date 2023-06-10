package hwr.oop.todo.ui.cli.menus;

import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskStatus;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

import java.io.PrintStream;
import java.time.LocalDate;

public class MainMenu extends InputOptionsMenu {

    public final MenuActionHandler actionHandler;
    public final String[] menuHeadline = {"ToDo-List of: %s",
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
            "",
            "[MainMenu]"};

    public MainMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
        this.out = out;
        this.in = in;

        actionHandler = new MenuActionHandler(1, out, in);
        actionHandler.addAction("Create Task [quick]", this::createSimpleTask);
        actionHandler.addAction("Create task [detailed]", null);
        actionHandler.addAction("Show Tasks", null);
        actionHandler.addAction("Edit Tasks", null);
        actionHandler.addAction("Create Project", null);
        actionHandler.addAction("Edit Project", null);
        actionHandler.addAction("Show Project", null);
        actionHandler.addAction("Edit Todo-List", null);
        actionHandler.addAction("Go Back", parentMenu::returnToMe);
    }

    @Override
    public void open() {
        if (Main.activeTodo().owner().isPresent()) {
            printMenu(out, actionHandler, menuHeadline, Main.activeTodo().owner().get());
        } else {
            printMenu(out, actionHandler, menuHeadline, null);
        }
    }
    
    public static final String CREATE_SIMPLE_TASK_MSG = "Enter the name of the task you want to create.";
    public static final String CREATE_SIMPLE_TASK_ERROR_MSG = "Invalid (empty) task name.";
    public static final String CREATE_SIMPLE_TASK_SUCCESS_MSG = "Successfully created the task %s";
    private void createSimpleTask() {
    	String taskName = Console.promptForString(out, in, true, CREATE_SIMPLE_TASK_MSG, CREATE_SIMPLE_TASK_ERROR_MSG);
    	
    	Task t = new Task.Builder(taskName).build();
    	Main.activeTodo().addTask(t);
    	
    	out.println(String.format(CREATE_SIMPLE_TASK_SUCCESS_MSG, taskName));
    	returnToMe();
    }

    public static final String CREATE_TASK_DATE_MSG = "Please enter a date for your task in the format YYYY-MM-DD";
    private void createTask() {
    	String taskName = Console.promptForString(out, in, true, CREATE_SIMPLE_TASK_MSG, CREATE_SIMPLE_TASK_ERROR_MSG);
    	
    	LocalDate taskDate = Console.promptForDate(out, in, false, CREATE_TASK_DATE_MSG, "Invalid date format!");
    	
    	Task t = new Task.Builder(taskName).deadline(taskDate).status(TaskStatus.DONE).build();
    	Main.activeTodo().addTask(t);
    }
}
