package hwr.oop.todo;

import java.time.LocalDate;

public class Project {
    private final String title;
    private LocalDate deadline;

    public Project(String title , LocalDate deadline) {
        this.title = title;
        this.deadline = deadline;
    }
    /*

    public Task taskByTitle(String title) {
        for (Task task: this.task_list) {
            if (task.title() == title) {
                return task;
            }
        }
        return null;
    }

    public ArrayList<Task> taskByTagname(String tagname) {
        ArrayList<Task> list = new ArrayList<Task>();
        for (Task task: this.task_list) {
            for(TaskTag tag: task.tags()) {
                if (tagname == tag.title()) {
                    list.add(task);
                }
            }
        }
        return list;
    }

    public Task updateTask() {
        return null;
    }
*/
    public String title() {
        return this.title;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }
}