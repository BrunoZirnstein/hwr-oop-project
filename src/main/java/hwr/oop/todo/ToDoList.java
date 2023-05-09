package hwr.oop.todo;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToDoList {
    private final List<Task> tasks;
    private final List<Project> projects;
    private String user;

    public ToDoList(String user) {
        this.user = user;
        this.tasks = new ArrayList();
        this.projects = new ArrayList();
    }

    public List<Task> tasks() {
        return this.tasks;
    }

    public List<Project> projects() {
        return this.projects;
    }

    public String user() {
        return this.user;
    }

    public void updateOwner(String name) {
        this.user = name;
    }

    public void addTask(Task task) {
        try {
            if (task.projectName().isPresent()) {
                this.getProjectByName((String)task.projectName().get());
            }

            this.tasks.add(task);
        } catch (InvalidParameterException var3) {
            throw new InvalidParameterException("Cannot add project to ToDo list, given project name does not exist.");
        }
    }

    public void removeTaskByObject(Task task) {
        this.tasks.remove(task);
    }

    public void removeTaskByName(String taskName) {
        this.tasks.removeIf((task) -> {
            return task.title().equals(taskName);
        });
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void removeProject(String projectName) {
        Project projectObject = this.getProjectByName(projectName);
        this.removeProjectByObject(projectObject);
    }

    public void removeProjectByObject(Project project) {
        this.projects.remove(project);
    }

    public Task taskByTitle(String title) {
        return (Task)this.tasks.stream().filter((task) -> {
            return task.title().equals(title);
        }).findFirst().orElseThrow(() -> {
            return new InvalidParameterException("Cannot get task from ToDo list, given task title does not exist.");
        });
    }

    public List<Task> taskByTagName(String tagName) {
        return this.tasks.stream().filter((task) -> {
            return task.tags().stream().anyMatch((tag) -> {
                return tag.title().equals(tagName);
            });
        }).toList();
    }

    public List<Task> taskByProject(String projectName) {
        if (this.projects.stream().filter((project) -> {
            return project.title().equals(projectName);
        }).count() == 0L) {
            throw new InvalidParameterException("Cannot get tasks from ToDo list, given project name does not exist.");
        } else {
            ArrayList<Task> tasksWithProject = new ArrayList();
            Iterator var3 = this.tasks.iterator();

            while(var3.hasNext()) {
                Task task = (Task)var3.next();
                if (!task.projectName().isEmpty() && projectName.equals(task.projectName().get())) {
                    tasksWithProject.add(task);
                }
            }

            return tasksWithProject;
        }
    }

    public Project getProjectByName(String projectName) {
        if (this.projects.isEmpty()) {
            throw new InvalidParameterException("Cannot get project object from ToDo list, given project name does not exist.");
        } else {
            List<Project> projectObjects = this.projects.stream().filter((project) -> {
                return project.title().equals(projectName);
            }).toList();
            if (projectObjects.isEmpty()) {
                throw new InvalidParameterException("Cannot get project object from ToDo list, given project name does not exist.");
            } else {
                return (Project)projectObjects.get(0);
            }
        }
    }
}
