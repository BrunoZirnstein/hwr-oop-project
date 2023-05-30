package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

import java.util.List;

public interface TasksByProjectInPort {
    public List<Task> tasksByProjectInPort(
            TasksByProjectInPort.TasksByProjectCommand tasksByProjectCommand);

    public class TasksByProjectCommand {
        private ToDoList list;
        private Project project;

        private TasksByProjectCommand(ToDoList list, Project project) {
            this.list = list;
            this.project = project;
        }
    }
}
