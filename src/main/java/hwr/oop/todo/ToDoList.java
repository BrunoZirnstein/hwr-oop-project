package hwr.oop.todo;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private final List<Task> tasks;
    private final List<Project> projects;
    private String user;

    public ToDoList(String user) {
        this.user = user;
        this.tasks = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public List<Task> tasks() {
        return tasks;
    }

    public List<Project> projects() {
        return projects;
    }

    public String user() {
        return user;
    }

    public void updateOwner(String name) {
        user = name;
    }

    public void addTask(Task task) {
        try {
            if (task.projectName().isPresent()) {
                getProjectByName(task.projectName().get());
            }

            tasks.add(task);
        } catch (InvalidParameterException e) {
            throw new InvalidParameterException(
                    "Cannot add project to ToDo list, given project name does not exist.");
        }
    }

    public void removeTaskByObject(Task task) {
        tasks.remove(task);
    }

    public void removeTaskByName(String taskName) {
        tasks.removeIf(task -> task.title().equals(taskName));
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(String projectName) {
        Project projectObject = getProjectByName(projectName);
        removeProjectByObject(projectObject);
    }

    public void removeProjectByObject(Project project) {
        this.projects.remove(project);
    }

    public Task taskByTitle(String title) {
        return tasks.stream().filter(task -> task.title().equals(title))
                .findFirst().orElseThrow(() -> new InvalidParameterException(
                        "Cannot get task from ToDo list, given task title does not exist."));
    }

    public List<Task> taskByTagName(String tagName) {
        return this.tasks.stream().filter(task -> task.tags().stream()
                .anyMatch(tag -> tag.title().equals(tagName))).toList();
    }

    public List<Task> taskByProject(String projectName) {
        if (projects.stream()
                .noneMatch(project -> project.title().equals(projectName))) {
            throw new InvalidParameterException(
                    "Cannot get tasks from ToDo list, given project name does not exist.");
        }

        return tasks.stream().filter(task -> task.projectName()
                        .isPresent() && projectName.equals(task.projectName().get()))
                .toList();
    }

    public Project getProjectByName(String projectName) {
        if (projects.isEmpty()) {
            throw new InvalidParameterException(
                    "Cannot get project object from ToDo list, given project name does not exist.");
        }

        List<Project> projectObjects = projects.stream()
                .filter(project -> project.title().equals(projectName))
                .toList();

        if (projectObjects.isEmpty()) {
            throw new InvalidParameterException(
                    "Cannot get project object from ToDo list, given project name does not exist.");
        }

        return projectObjects.get(0);
    }
}
