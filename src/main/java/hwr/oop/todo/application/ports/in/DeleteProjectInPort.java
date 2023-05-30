package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface DeleteProjectInPort {
    public void deleteProjectInPort(
            DeleteProjectInPort.DeleteProjectCommand deleteProjectCommand);

    public class DeleteProjectCommand {
        private ToDoList list;
        private Project project;

        private DeleteProjectCommand(ToDoList list, Project project) {
            this.list = list;
            this.project = project;
        }
    }
}
