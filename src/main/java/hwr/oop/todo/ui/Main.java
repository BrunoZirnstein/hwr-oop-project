package hwr.oop.todo.ui;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MainMenu;

import java.util.Scanner;

public class Main {

    public static ToDoList activeTodo = null;
    public static MainMenu mainMenu = new MainMenu(System.out, new InputHandler(new Scanner(System.in), -1));

    public static void main(String[] args) {
        mainMenu.open();
    }

}