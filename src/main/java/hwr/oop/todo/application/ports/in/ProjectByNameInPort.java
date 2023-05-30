package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface ProjectByNameInPort {
    public Project projectByNameInPort(
            ProjectByNameInPort.ProjectByNameCommand projectByNameCommand);

    public class ProjectByNameCommand {
        private ToDoList list;
        private String projectName;

        private ProjectByNameCommand(ToDoList list, String projectName) {
            this.list = list;
            this.projectName = projectName;
        }
    }
}
