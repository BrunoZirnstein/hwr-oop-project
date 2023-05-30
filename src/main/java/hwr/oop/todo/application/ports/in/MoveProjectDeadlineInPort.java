package hwr.oop.todo.application.ports.in;

import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

import java.time.LocalDate;

public interface MoveProjectDeadlineInPort {
    public void moveProjectDeadlineInPort(
            MoveProjectDeadlineInPort.MoveProjectDeadlineCommand moveProjectDeadlineCommand);

    public class MoveProjectDeadlineCommand {
        private ToDoList list;
        private Project project;
        private LocalDate newDeadline;

        private MoveProjectDeadlineCommand(ToDoList list, Project project,
                                           LocalDate newDeadline) {
            this.list = list;
            this.project = project;
            this.newDeadline = newDeadline;
        }
    }
}
