package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface RemoveTaskProjectInPort {
    public void removeTaskProject(
            RemoveTaskProjectInPort.RemoveTaskProjectCommand removeTaskProjectCommand);

    public class RemoveTaskProjectCommand {
        private ToDoList list;
        private Task task;

        private RemoveTaskProjectCommand(ToDoList list, Task task) {
            this.list = list;
            this.task = task;
        }
    }
}
