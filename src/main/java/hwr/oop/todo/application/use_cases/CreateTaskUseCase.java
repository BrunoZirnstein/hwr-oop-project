package hwr.oop.todo.application.use_cases;

import hwr.oop.todo.application.ports.in.CreateTaskInPort;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;
import hwr.oop.todo.core.ToDoList;

public class CreateTaskUseCase implements CreateTaskInPort {
    LoadListByIdOutPort loadListByIdOutPort;
    OverwriteListOutPort overwriteListOutPort;

    public CreateTaskUseCase(LoadListByIdOutPort loadListByIdOutPort,
                             OverwriteListOutPort overwriteListOutPort) {
        this.loadListByIdOutPort = loadListByIdOutPort;
        this.overwriteListOutPort = overwriteListOutPort;
    }

    public void createTask(CreateTaskCommand createTaskCommand) {
        ToDoList list = loadListByIdOutPort.loadListById(
                createTaskCommand.listId());
        list.addTask(createTaskCommand.newTask());
        overwriteListOutPort.overwriteList(list);
    }
}
