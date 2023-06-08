package hwr.oop.todo.ui.cli.atarashii;

import hwr.oop.todo.ui.Main;
import hwr.oop.todo.ui.cli.CTestHelper;
import hwr.oop.todo.ui.cli.Console;
import hwr.oop.todo.ui.cli.InputHandler;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class StartMenuTest {

    public static String getMenuHeadline(StartMenu menu) {
        return String.join(System.lineSeparator(), menu.menuHeadline);
    }

    @Test
    void Test_open() {
        OutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = CTestHelper.createInputStreamForInput("2\n");
        Scanner in = new Scanner(inputStream);
        InputHandler inputHandler = new InputHandler(in, 1);

        StartMenu menu = new StartMenu(new PrintStream(out), inputHandler);
        menu.open();

        // check if menu has expected actions count
        int expectedActionCount = 2;
        assertThat(menu.inputHandler.getCount()).isEqualTo(expectedActionCount);

        // check if menu is properly displayed
        assertThat(out.toString()).contains(getMenuHeadline(menu));
        assertThat(out.toString()).contains(menu.inputHandler.getMenuPrintString());
        assertThat(out.toString()).contains(Console.DISPLAY_INPUT_INDICATOR_STR);
    }

    @Test
    void Test_loadOrCreateTodoList() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String todoListName = "Bernd das Brot";

        OutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = CTestHelper.createInputStreamForInput(todoListName + "\n");    // 1 for loading / creating the ToDo-list
        Scanner in = new Scanner(inputStream);
        InputHandler inputHandler = new InputHandler(in, 1);

        StartMenu menu = new StartMenu(new PrintStream(out), inputHandler);

        Method f = menu.getClass().getDeclaredMethod("loadOrCreateTodoList");
        f.setAccessible(true);
        f.invoke(menu, (Object[]) null);

        assertThat(out.toString()).contains(StartMenu.LOAD_OR_CREATE_SUCCESS_MSG_PREFIX);

        // check if function created ToDo-List successfully
        assertThat(Main.activeTodo()).isNotNull();
        assertThat(Main.activeTodo().owner()).contains(todoListName);

        // check if main menu is displayed
        assertThat(out.toString()).contains(MainMenuTest.getMainMenuHeadline());
    }

    @Test
    void Test_returnToMe() {
        OutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = CTestHelper.createInputStreamForInput("");
        Scanner in = new Scanner(inputStream);
        InputHandler inputHandler = new InputHandler(in, 0);

        StartMenu menu = new StartMenu(new PrintStream(out), inputHandler);
        menu.returnToMe();

        assertThat(out.toString()).contains(Console.ENTER_TO_CONTINUE_MESSAGE);
    }
}
