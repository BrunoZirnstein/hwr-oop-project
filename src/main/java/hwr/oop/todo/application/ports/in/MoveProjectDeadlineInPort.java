package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

import java.time.LocalDate;

public interface MoveProjectDeadlineInPort {
    public void moveProjectDeadline(
            MoveProjectDeadlineInPort.MoveProjectDeadlineCommand moveProjectDeadlineCommand);

    public class MoveProjectDeadlineCommand {
        public ToDoList list;
        public Project project;
        public LocalDate newDeadline;

        private MoveProjectDeadlineCommand(ToDoList list, Project project,
                                           LocalDate newDeadline) {
            this.list = list;
            this.project = project;
            this.newDeadline = newDeadline;
        }
    }
}
