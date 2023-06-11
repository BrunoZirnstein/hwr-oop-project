package hwr.oop.todo.application.use_cases;

import hwr.oop.todo.application.ports.in.CreateListInPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;
import hwr.oop.todo.core.ToDoList;

public class CreateListUseCase implements CreateListInPort {
    OverwriteListOutPort overwriteListOutPort;

    public CreateListUseCase(OverwriteListOutPort overwriteListOutPort) {
        this.overwriteListOutPort = overwriteListOutPort;
    }

    public ToDoList createList(CreateListCommand createListCommand) {
        ToDoList newList = new ToDoList(createListCommand.owner());
        overwriteListOutPort.overwriteList(newList);
        return newList;
    }
}
