package hwr.oop.todo;

import java.util.ArrayList;
import java.util.Objects;

public class ToDo {
    private String user;
    private ArrayList<Task> tasks;

    public ToDo(String user) {
        this.user = user;
        this.tasks = new ArrayList<>();
    }

    public String user() {
        return this.user;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public ArrayList<Task> tasks() {
        return this.tasks;
    }
    public Task taskByTitle(String title) {
        for (Task task: this.tasks) {
            if (Objects.equals(task.title(), title)) {
                return task;
            }
        }
        return null;
    }
    public ArrayList<Task> taskByTagname(String tagname) {
        ArrayList<Task> list = new ArrayList<Task>();
        for (Task task: this.tasks) {
            for(TaskTag tag: task.tags()) {
                if (Objects.equals(tagname, tag.title())) {
                    list.add(task);
                }
            }
        }
        return list;
    }

    public ArrayList<Task> taskByProject(String projectname) {
        ArrayList<Task> list = new ArrayList<Task>();
        for (Task task: this.tasks) {
            if (Objects.equals(projectname, task.project().title())) {
                list.add(task);
            }

        }
        return list;
    }

    public Task updateTask() {
        return null;
    }
}
