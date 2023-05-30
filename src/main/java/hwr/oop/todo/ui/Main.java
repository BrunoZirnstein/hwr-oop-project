package hwr.oop.todo.ui;

import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.ui.cli.InputHandler;
import hwr.oop.todo.ui.cli.MainMenu;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static ToDoList activeTodo = null;
    public static MainMenu mainMenu = new MainMenu(System.out, new InputHandler(new Scanner(System.in), -1));

    // I love comments :P
    public static void main(String[] args) throws FileNotFoundException {
        mainMenu.open();
    }

}

/*
 * ToDo: Nutzer
      - Projekt A
        -> Task 1
      - Projekt
        -> Task 1.1

    Aber im Code
        Todo -> Task ; Projekt
        Task -> Projekt
 */