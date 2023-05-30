package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

import java.util.List;

public interface TasksByProjectInPort {
    public List<Task> tasksByProject(
            TasksByProjectInPort.TasksByProjectCommand tasksByProjectCommand);

    public class TasksByProjectCommand {
        public ToDoList list;
        public Project project;

        private TasksByProjectCommand(ToDoList list, Project project) {
            this.list = list;
            this.project = project;
        }
    }
}
