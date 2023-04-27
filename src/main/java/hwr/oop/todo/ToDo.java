package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ToDo {
    private String user;
    private List<Task> tasks;
    private List<Project> projects;

    public ToDo(String user) {
        this.user = user;
        tasks = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public List<Task> tasks() {
        return tasks;
    }

    public Optional<List<Project>> projects() {
        return Optional.ofNullable(projects);
    }

    public String user() {
        return user;
    }

    public void addTask(Task task) {
        try {
            if (task.projectName().isPresent()) {
                getProjectByName(task.projectName().get());
            }
        } catch (ArrayIndexOutOfBoundsException  e) {
            throw new RuntimeException("Cannot add project to ToDo list, given project name does not exist.");
        }

        tasks.add(task);
    }

    public void removeTaskByObject(Task task) {
        tasks.remove(task);
    }

    public void removeTaskByName(String taskname) {
        this.tasks.removeIf(task -> task.title().equals(taskname));
    }


    public void createProject(Project project) {
        projects.add(project);
    }

    public void deleteProject(Project project) {
        projects.remove(project);
    }

    public Task taskByTitle(String title) {
        for (Task task : tasks) {
            if (Objects.equals(task.title(), title)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> taskByTagname(String tagname) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks) {
            for (TaskTag tag : task.tags()) {
                if (Objects.equals(tagname, tag.title())) {
                    list.add(task);
                }
            }
        }
        return list;
    }

    public List<Task> taskByProject(String projectname) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks) {
            if (task.projectName().isEmpty()) continue;
            if (Objects.equals(projectname, task.projectName().get())) {
                list.add(task);
            }

        }
        return list;
    }

    public void addProject(Project newProject) {
        projects.add(newProject);
    }

    public void removeProject(String projectName) {
        Project projectObject = getProjectByName(projectName);
        projects.remove(projectObject);
    }

    public Project getProjectByName(String projectName) {
        List<Project> filteredProjects = projects.stream().filter(p -> p.title().equals(projectName)).toList();
        return filteredProjects.get(0);
    }
}
