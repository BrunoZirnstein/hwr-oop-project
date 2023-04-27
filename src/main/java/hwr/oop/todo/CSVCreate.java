package hwr.oop.todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVCreate {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_SEPARATOR = "\n";

    public static void writeToDoFile(Task task,ToDo todo, String filePathToDo) throws IOException {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePathToDo);

            fileWriter.append(task.title());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(task.description());
            for (TaskTag tag : task.tags()) {
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(tag.title());
            }
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(task.deadline().toString());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(task.status().name());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(task.priority().name());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(task.project().title());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(todo.user());
            fileWriter.append(LINE_SEPARATOR);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Successfully created CSV-Task-File!");

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Creation!");
        }
    }
    public static void writeProjectFile(Project project, String filePathProject) throws IOException {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePathProject);

            fileWriter.append(project.title());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(project.getDeadline().toString());
            fileWriter.append(LINE_SEPARATOR);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Successfully created CSV-Project-File!");

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Creation!");
        }
    }
}