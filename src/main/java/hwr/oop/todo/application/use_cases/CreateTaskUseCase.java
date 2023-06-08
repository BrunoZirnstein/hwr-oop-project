package hwr.oop.todo.application.use_cases;

import hwr.oop.todo.application.ports.in.CreateTaskInPort;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;
import hwr.oop.todo.core.Task;
import hwr.oop.todo.core.ToDoList;

public class CreateTaskUseCase implements CreateTaskInPort {
    LoadListByIdOutPort loadListByIdOutPort;
    OverwriteListOutPort overwriteListOutPort;

    public CreateTaskUseCase(LoadListByIdOutPort loadListByIdOutPort,
                             OverwriteListOutPort overwriteListOutPort) {
        this.loadListByIdOutPort = loadListByIdOutPort;
        this.overwriteListOutPort = overwriteListOutPort;
    }

    public Task createTask(CreateTaskCommand createTaskCommand) {
        Task newTask = new Task.Builder(createTaskCommand.title()).description(
                        createTaskCommand.description()).tags(createTaskCommand.tags())
                .deadline(createTaskCommand.deadline())
                .projectName(createTaskCommand.projectName())
                .priority(createTaskCommand.priority())
                .build();

        ToDoList list = loadListByIdOutPort.loadListById(
                createTaskCommand.listId());
        list.addTask(newTask);
        overwriteListOutPort.overwriteList(list);
        return newTask;
    }
}
