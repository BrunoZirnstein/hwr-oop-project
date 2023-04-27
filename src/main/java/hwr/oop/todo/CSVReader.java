package hwr.oop.todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private static final String COMMA_DELIMITER = ",";

    public static List<ToDo> readToDoFile(String filePathToDo) throws IOException {
        BufferedReader fileReader = null;
        List<ToDo> todoList = new ArrayList<>();

        try {
            fileReader = new BufferedReader(new FileReader(filePathToDo));

            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);

                String taskTitle = tokens[0];
                String taskDescription = tokens[1];
                List<TaskTag> tags = new ArrayList<>();
                for (int i = 2; i < tokens.length - 4; i++) {
                    tags.add(new TaskTag(tokens[i]));
                }
                LocalDate deadline = LocalDate.parse(tokens[tokens.length - 4]);
                TaskStatus status = TaskStatus.valueOf(tokens[tokens.length - 3]);
                TaskPriority priority = TaskPriority.valueOf(tokens[tokens.length - 2]);
                String projectTitle = tokens[tokens.length - 1];
                Project project = new Project(projectTitle,null);
                Task task = new Task.Builder(taskTitle).description(taskDescription).tags(tags).deadline(deadline).priority(priority).projectName(projectTitle).build();
                ToDo todo = new ToDo(tokens[tokens.length - 5]);
                todo.addTask(task);
                todoList.add(todo);
            }
            fileReader.close();
            System.out.println("Successfully read CSV-Task-File!");

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Reading!");
        }

        return todoList;
    }

    public static List<Project> readProject(String filePathProject) throws IOException {
        BufferedReader fileReader = null;
        List<Project> projectList = new ArrayList<>();

        try {
            fileReader = new BufferedReader(new FileReader(filePathProject));

            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);

                String projectTitle = tokens[0];
                LocalDate deadline = null;
                if (!tokens[1].equals("")) {
                    deadline = LocalDate.parse(tokens[1]);
                }
                Project project = new Project(projectTitle, deadline);
                projectList.add(project);
            }

            fileReader.close();
            System.out.println("Successfully read CSV-Project-File!");

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error in CSV-Reading!");
        }

        return projectList;
    }
}

