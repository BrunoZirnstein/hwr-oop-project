package hwr.oop.todo.ui;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.cli.MainMenu;


public class Main {

    public static ToDoList activeTodo = null;
    public static MainMenu mainMenu = new MainMenu(System.out, System.in);

    public static void main(String[] args) {
        mainMenu.open();
    }

}