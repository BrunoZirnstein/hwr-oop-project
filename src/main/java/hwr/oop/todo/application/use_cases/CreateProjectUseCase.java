package hwr.oop.todo.application.use_cases;

import hwr.oop.todo.application.ports.in.CreateProjectInPort;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;
import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;

public class CreateProjectUseCase implements CreateProjectInPort {
    LoadListByIdOutPort loadListByIdOutPort;
    OverwriteListOutPort overwriteListOutPort;

    public CreateProjectUseCase(LoadListByIdOutPort loadListByIdOutPort,
                                OverwriteListOutPort overwriteListOutPort) {
        this.loadListByIdOutPort = loadListByIdOutPort;
        this.overwriteListOutPort = overwriteListOutPort;
    }

    @Override
    public void createProject(CreateProjectCommand createProjectCommand) {
        Project newProject = new Project.Builder(
                createProjectCommand.projectOwner()).deadline(
                createProjectCommand.projectDeadline()).build();

        ToDoList list = loadListByIdOutPort.loadListById(
                createProjectCommand.listId());
        list.addProject(newProject);
        overwriteListOutPort.overwriteList(list);
    }
}
