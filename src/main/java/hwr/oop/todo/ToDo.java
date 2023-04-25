package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToDo {
    private String user;
    private List<Task> tasks;
    private List<Project> projects;

    public ToDo(String user) {
        this.user = user;
        tasks = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public String user() {
        return user;
    }

    public void addTask(Task task) {
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

    public List<Task> tasks() {
        return tasks;
    }
    public Task taskByTitle(String title) {
        for (Task task: tasks) {
            if (Objects.equals(task.title(), title)) {
                return task;
            }
        }
        return null;
    }
    public List<Task> taskByTagname(String tagname) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task: tasks) {
            for(TaskTag tag: task.tags()) {
                if (Objects.equals(tagname, tag.title())) {
                    list.add(task);
                }
            }
        }
        return list;
    }

    public List<Task> taskByProject(String projectname) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task: tasks) {
            if (task.project().isEmpty()) continue;
            if (Objects.equals(projectname, task.project().get().title())) {
                list.add(task);
            }

        }
        return list;
    }
}
