package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public interface RenameProjectInPort {
    public void renameProject(
            RenameProjectInPort.RenameProjectCommand renameProjectCommand);

    public class RenameProjectCommand {
        public ToDoList list;
        public Project project;
        public String newName;

        private RenameProjectCommand(ToDoList list, Project project,
                                     String newName) {
            this.list = list;
            this.project = project;
            this.newName = newName;
        }
    }
}
