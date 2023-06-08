package hwr.oop.todo.ui;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MainMenu_old;
import hwr.oop.todo.ui.cli.atarashii.StartMenu;

import java.util.Scanner;

public class Main {

    public static ToDoList activeTodo = null;
    public static MainMenu_old mainMenu = new MainMenu_old(System.out, new InputHandler(new Scanner(System.in), -1));
    public static StartMenu startMenu = new StartMenu(System.out, new InputHandler(new Scanner(System.in), -1));

    public static void main(String[] args) {
        //mainMenu.open();
    	startMenu.open();
    }

}