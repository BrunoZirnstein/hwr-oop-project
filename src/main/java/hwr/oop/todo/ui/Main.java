package hwr.oop.todo.ui;

import hwr.oop.todo.application.ports.in.CreateListInPort;
import hwr.oop.todo.application.ports.in.CreateProjectInPort;
import hwr.oop.todo.application.ports.in.CreateTaskInPort;
import hwr.oop.todo.application.use_cases.CreateListUseCase;
import hwr.oop.todo.application.use_cases.CreateProjectUseCase;
import hwr.oop.todo.application.use_cases.CreateTaskUseCase;
import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.persistence.PersistenceAdapter;
import hwr.oop.todo.persistence.csv.CSVHandler;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.menus.StartMenu;
import hwr.oop.todo.application.ports.in.CreateListInPort;
import hwr.oop.todo.application.use_cases.CreateListUseCase;
import hwr.oop.todo.application.use_cases.CreateProjectUseCase;
import hwr.oop.todo.application.use_cases.CreateTaskUseCase;
import hwr.oop.todo.application.ports.in.CreateProjectInPort;
import hwr.oop.todo.application.ports.in.CreateTaskInPort;

import java.util.Scanner;

public class Main {

    public static final PersistenceAdapter persistenceAdapter = new CSVHandler();
    public static final CreateListInPort createListInPort = new CreateListUseCase(persistenceAdapter);
    public static final CreateTaskInPort createTaskInPort = new CreateTaskUseCase(persistenceAdapter,
            persistenceAdapter);
    public static final CreateProjectInPort createProjectInPort = new CreateProjectUseCase(persistenceAdapter,
            persistenceAdapter);
    private static ToDoList activeTodo = null;

    public static final PersistenceAdapter PERSISTENCE_ADAPTER = new CSVHandler();
    public static final CreateListInPort CREATE_LIST_IN_PORT = new CreateListUseCase(PERSISTENCE_ADAPTER);
    public static final CreateProjectInPort CREATE_PROJECT_IN_PORT = new CreateProjectUseCase(PERSISTENCE_ADAPTER,
            PERSISTENCE_ADAPTER);
    public static final CreateTaskInPort CREATE_TASK_IN_PORT = new CreateTaskUseCase(PERSISTENCE_ADAPTER,
            PERSISTENCE_ADAPTER);

    // I love comments :P
    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu(System.out, new InputHandler(new Scanner(System.in), -1)); // start menu
                                                                                                       // creation must
                                                                                                       // take place
                                                                                                       // here for
                                                                                                       // UnitTest
        startMenu.open();
    }

    public static ToDoList activeTodo() {
        return activeTodo;
    }

    public static void changeActiveTodo(ToDoList newActiveTodo) {
        activeTodo = newActiveTodo;
    }
}