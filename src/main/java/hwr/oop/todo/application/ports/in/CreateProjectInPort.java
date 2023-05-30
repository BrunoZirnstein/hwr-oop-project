package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface CreateProjectInPort {
    public void createProjectInPort(
            CreateProjectInPort.CreateProjectCommand createProjectCommand);

    public class CreateProjectCommand {
        private ToDoList list;
        private Project newProject;

        private CreateProjectCommand(ToDoList list, Project newProject) {
            this.list = list;
            this.newProject = newProject;
        }
    }
}
