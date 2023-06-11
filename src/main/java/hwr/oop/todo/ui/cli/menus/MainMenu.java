package hwr.oop.todo.ui.cli.menus;

import hwr.oop.todo.application.ports.in.CreateTaskInPort;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.TaskPriority;
import hwr.oop.todo.core.TaskStatus;
import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MenuActionHandler;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;

import static hwr.oop.todo.application.ports.in.CreateTaskInPort.*;

public class MainMenu extends InputOptionsMenu {

    private final DisplayTaskMenu displayTaskMenu;
    public final MenuActionHandler actionHandler;
    public final String[] menuHeadline = {"ToDo-List of: %s",
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
            "",
            "[MainMenu]"};

    public MainMenu(PrintStream out, InputHandler in, InputOptionsMenu parentMenu) {
        this.out = out;
        this.in = in;
        
        displayTaskMenu = new DisplayTaskMenu(out, in, this);

        actionHandler = new MenuActionHandler(1, out, in);
        actionHandler.addAction("Create Task [quick]", this::createSimpleTask);
        actionHandler.addAction("Create task [detailed]", this::createTask);
        actionHandler.addAction("Show Tasks", displayTaskMenu::open);
        actionHandler.addAction("Edit Tasks", null);
        actionHandler.addAction("Create Project", null);
        actionHandler.addAction("Edit Project", null);
        actionHandler.addAction("Show Project", null);
        actionHandler.addAction("Edit Todo-List", null);
        actionHandler.addAction("Go Back", parentMenu::returnToMe);
    }

    @Override
    public void open() {
        printMenu(out, actionHandler, menuHeadline, Main.activeTodo().owner().orElse(null));
    }
    
    public static final String CREATE_SIMPLE_TASK_MSG = "Enter the name of the task you want to create.";
    public static final String CREATE_SIMPLE_TASK_ERROR_MSG = "Invalid (empty) task name.";
    public static final String CREATE_SIMPLE_TASK_SUCCESS_MSG = "Successfully created the task %s";
    private void createSimpleTask() {
        String taskName = Console.promptForString(out, in, true, CREATE_SIMPLE_TASK_MSG, CREATE_SIMPLE_TASK_ERROR_MSG);

        CreateTaskCommand createTaskCommand = new CreateTaskCommand(Main.activeTodo().id(), taskName, null, null, null, null, null);
        Task newTask = Main.createTaskInPort.createTask(createTaskCommand);
        Main.activeTodo().addTask(newTask);

        out.println(String.format(CREATE_SIMPLE_TASK_SUCCESS_MSG, taskName));
        returnToMe();
    }

    public static final String CREATE_TASK_DATE_MSG = "Please enter a endline date for your task in the format YYYY-MM-DD";
    public static final String CREATE_TASK_DESCR_MSG = "Please enter a task description or press ENTER to leave blank";
    public static final String CREATE_TASK_STATUS_MSG_PREFIX = "Please enter a task status. The following task status'es are available:" + System.lineSeparator() + "%s";
    public static final String CREATE_TASK_STATUS_ERROR_MSG = "No valid status was entered. Please enter a valid status.";
    public static final String CREATE_TASK_PRIORITY_MSG_PREFIX = "Please enter a task priority. The following task-priorities are available: " + System.lineSeparator() + "%s";
    public static final String CREATE_TASK_PRIORITY_ERROR_MSG = "No valid task priority was entered. Please enter a valid task priority.";
    private void createTask() {
        String taskName = Console.promptForString(out, in, true, CREATE_SIMPLE_TASK_MSG, CREATE_SIMPLE_TASK_ERROR_MSG);

        String description = Console.promptForString(out, in, false, CREATE_TASK_DESCR_MSG, null);

        LocalDate deadline = Console.promptForDate(out, in, false, CREATE_TASK_DATE_MSG, "Invalid date format!");

        String msg = String.format(CREATE_TASK_PRIORITY_MSG_PREFIX, Arrays.toString(TaskPriority.values()));
        TaskPriority priority = Console.promptForEnum(TaskPriority.class, out, in, false, false, msg, CREATE_TASK_PRIORITY_ERROR_MSG);

        msg = String.format(CREATE_TASK_STATUS_MSG_PREFIX, Arrays.toString(TaskStatus.values()));
        TaskStatus status = Console.promptForEnum(TaskStatus.class, out, in, false, false, msg, CREATE_TASK_STATUS_ERROR_MSG);

        CreateTaskCommand createTaskCommand = new CreateTaskCommand(Main.activeTodo().id(), taskName, description, null, deadline, null, priority);
        Task newTask = Main.createTaskInPort.createTask(createTaskCommand);
        Main.activeTodo().addTask(newTask);

        out.println(String.format(CREATE_SIMPLE_TASK_SUCCESS_MSG, taskName));
        returnToMe();
    }
}
