package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public interface SwitchTaskProjectInPort {
    public void switchTaskProject(
            SwitchTaskProjectInPort.SwitchTaskProjectCommand switchTaskProjectCommand);

    public class SwitchTaskProjectCommand {
        public ToDoList list;
        public Task task;
        public Project newProject;

        private SwitchTaskProjectCommand(ToDoList list, Task task,
                                         Project newProject) {
            this.list = list;
            this.task = task;
            this.newProject = newProject;

        }
    }
}
