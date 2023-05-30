package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface SwitchTaskProjectInPort {
    public void switchTaskProjectInPort(
            SwitchTaskProjectInPort.SwitchTaskProjectCommand switchTaskProjectCommand);

    public class SwitchTaskProjectCommand {
        private ToDoList list;
        private Task task;
        private Project newProject;

        private SwitchTaskProjectCommand(ToDoList list, Task task,
                                         Project newProject) {
            this.list = list;
            this.task = task;
            this.newProject = newProject;

        }
    }
}
