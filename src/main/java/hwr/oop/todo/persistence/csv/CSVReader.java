package hwr.oop.todo.persistence.csv;

import hwr.oop.todo.application.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    private static final String COMMA_DELIMITER = ",";

    private static final String FILEPATHTODO = "ToDo_List.csv";
    private static final String FILEPATHPROJECT = "Project_List.csv";

    public static String getFilePathTodo() {
        return FILEPATHTODO;
    }


    public static String getFilePathProject() {
        return FILEPATHPROJECT;
    }

    public static ToDoList readToDoFile(String user, String filePathToDo) throws IOException {
        ToDoList todo = new ToDoList(user);
        try (BufferedReader br = new BufferedReader(
                new FileReader(filePathToDo))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(","));
                String title = values.get(0);
                String description = values.get(1);
                List<TaskTag> tags = new ArrayList<>();
                if (!values.get(2).isEmpty()) {
                    String[] tagValues = values.get(2).split(";");
                    for (String tagValue : tagValues) {
                        tags.add(new TaskTag(tagValue));
                    }
                }
                LocalDate deadline = null;
                if (!values.get(3).isEmpty()) {
                    deadline = LocalDate.parse(values.get(3));
                }
                TaskStatus status = TaskStatus.valueOf(
                        values.get(4).toUpperCase());
                // Should be changed if all Tasks have a priority!
                TaskPriority priority = null;
                if (values.get(5).isEmpty()) {
                    priority = TaskPriority.valueOf("HIGH");
                } else {
                    priority = TaskPriority.valueOf(
                            values.get(5).toUpperCase());
                }
                // String projectName = values.get(6);

                if (values.get(7).equals(user)) {
                    Task task = new Task.Builder(title)
                            .description(description)
                            .tags(tags)
                            .deadline(deadline)
                            .status(status)
                            .priority(priority)
                            //.projectName(Optional.ofNullable(projectName).orElse("untitled"))
                            .build();
                    todo.addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filePathToDo, e);
        }
        return todo;
    }

    public static List<Project> readProjectFile(String filePathProject) throws IOException {
        List<Project> projects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(filePathProject))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(COMMA_DELIMITER);
                String title = fields[0];
                LocalDate deadline = LocalDate.parse(fields[1]);

                Project project = new Project(title, deadline);
                projects.add(project);
            }
        }

        return projects;
    }

}