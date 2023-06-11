package hwr.oop.todo.core;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ToDoList {
    private final ToDoListId id;
    private final List<Task> tasks;
    private final List<Project> projects;
    private String owner;

    public ToDoList(String owner) {
        this.id = new ToDoListId();
        this.owner = owner;
        this.tasks = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public ToDoList(ToDoListId id) {
        this.id = id;
        this.owner = null;
        this.tasks = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public ToDoList(ToDoListId id, String owner) {
        this.id = id;
        this.owner = owner;
        this.tasks = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoList toDoList = (ToDoList) o;
        return Objects.equals(id, toDoList.id) && Objects.equals(tasks, toDoList.tasks) && Objects.equals(projects, toDoList.projects) && Objects.equals(owner, toDoList.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tasks, projects, owner);
    }

    public ToDoListId id() {
        return id;
    }

    public List<Task> tasks() {
        return tasks;
    }

    public List<Project> projects() {
        return projects;
    }

    public Optional<String> owner() {
        return Optional.ofNullable(owner);
    }

    public void updateOwner(String name) {
        owner = name;
    }

    public void removeOwner() {
        owner = null;
    }

    public void addTask(Task task) {
        try {
            task.projectName().ifPresent(this::projectByName);

            tasks.add(task);
        } catch (InvalidParameterException e) {
            throw new InvalidParameterException(
                    "Cannot add task to ToDo list, given project name does not exist.");
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
        Project projectObject = projectByName(projectName);
        removeProjectByObject(projectObject);
    }

    public void removeProjectByObject(Project project) {
        this.projects.remove(project);
    }

    public Task taskById(TaskId id) {
        return tasks.stream().filter(task -> task.id().equals(id)).findFirst()
                .orElseThrow(() -> new InvalidParameterException(
                        "Cannot get task from ToDo list, given id does not exist."));
    }

    public Task taskByTitle(String title) {
        return tasks.stream().filter(task -> task.title().equals(title))
                .findFirst().orElseThrow(() -> new InvalidParameterException(
                        "Cannot get task from ToDo list, given task title does not exist."));
    }

    public List<Task> tasksByTagName(String tagName) {
        return this.tasks.stream().filter(task -> task.tags().stream()
                .anyMatch(tag -> tag.title().equals(tagName))).toList();
    }

    public List<Task> tasksByProject(String projectName) {
        boolean noProjectWithMatchingTitle = projects.isEmpty() || projects.stream()
                .noneMatch(project -> project.title().equals(projectName));

        if (noProjectWithMatchingTitle) {
            throw new InvalidParameterException(
                    "Cannot get tasks from ToDo list, given project name does not exist.");
        }

        return tasks.stream().filter(task -> task.projectName()
                        .isPresent() && task.projectName().get().equals(projectName))
                .toList();
    }

    public Project projectByName(String projectName) {
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
