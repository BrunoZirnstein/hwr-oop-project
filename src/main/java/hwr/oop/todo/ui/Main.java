package hwr.oop.todo.ui;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.persistence.PersistenceAdapter;
import hwr.oop.todo.persistence.csv.CSVHandler;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MainMenu;

import java.util.Scanner;

public class Main {

    private static ToDoList activeTodo = null;

    // I love comments :P
    public static void main(String[] args) {
    	PersistenceAdapter persistenceAdapter = new CSVHandler();
    	StartMenu startMenu = new StartMenu(System.out, new InputHandler(new Scanner(System.in), -1)); // start menu creation must take place here for UnitTest
        startMenu.open();
    }

    public static ToDoList activeTodo() {
        return activeTodo;
    }

    public static void changeActiveTodo(ToDoList newActiveTodo) {
        activeTodo = newActiveTodo;
    }
}