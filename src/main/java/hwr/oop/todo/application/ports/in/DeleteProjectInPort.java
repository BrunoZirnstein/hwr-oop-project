package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface DeleteProjectInPort {
    public void deleteProject(
            DeleteProjectInPort.DeleteProjectCommand deleteProjectCommand);

    public class DeleteProjectCommand {
        public ToDoList list;
        public Project project;

        private DeleteProjectCommand(ToDoList list, Project project) {
            this.list = list;
            this.project = project;
        }
    }
}
