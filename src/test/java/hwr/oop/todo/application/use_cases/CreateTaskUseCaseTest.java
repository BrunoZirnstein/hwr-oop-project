package hwr.oop.todo.application.use_cases;

import hwr.oop.todo.application.ports.in.CreateProjectInPort;
import hwr.oop.todo.application.ports.in.CreateTaskInPort;
import hwr.oop.todo.application.ports.in.CreateTaskInPort.CreateTaskCommand;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;
import hwr.oop.todo.core.*;
import hwr.oop.todo.persistence.PersistenceAdapter;
import hwr.oop.todo.persistence.csv.CSVHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static hwr.oop.todo.application.ports.in.CreateProjectInPort.CreateProjectCommand;
import static org.assertj.core.api.Assertions.assertThat;

class CreateTaskUseCaseTest {
    PersistenceAdapter persistenceAdapter = new CSVHandler();
    LoadListByIdOutPort loadListByIdOutPort = persistenceAdapter;
    OverwriteListOutPort overwriteListOutPort = persistenceAdapter;

    CreateTaskInPort createTaskInPort = new CreateTaskUseCase(
            persistenceAdapter, persistenceAdapter);
    CreateProjectInPort createProjectInPort = new CreateProjectUseCase(
            persistenceAdapter, persistenceAdapter);
    ToDoList list;

    @BeforeEach
    void setup() {
        ToDoList list = new ToDoList("Bruno");
        this.list = list;
        overwriteListOutPort.overwriteList(list);
    }

    @Test
    @DisplayName("Create task with title only")
    void createTask_TitleOnly() {
        String title = "Water plants";

        CreateTaskCommand createTaskCommand = new CreateTaskCommand(list.id(),
                title, null, null, null, null, null);
        Task savedTask = createTaskInPort.createTask(createTaskCommand);

        Task newTask = new Task.Builder(title).id(savedTask.id()).build();

        ToDoList savedList = loadListByIdOutPort.loadListById(list.id());
        List<Task> savedTasks = savedList.tasks();

        assertThat(savedTasks).containsExactly(newTask);
    }

    @Test
    @DisplayName("Create task with all attributes")
    void createTask_AllAttributes() {
        String title = "Water plants";
        String description = "Water all the plants in the living room and in the bedroom.";
        List<TaskTag> tags = List.of(new TaskTag("home"));
        LocalDate deadline = LocalDate.of(2023, 5, 30);
        String projectName = "university";
        TaskPriority priority = TaskPriority.HIGH;

        CreateProjectCommand createProjectCommand = new CreateProjectCommand(
                list.id(), projectName, null);
        Project createdProject = createProjectInPort.createProject(createProjectCommand);

        CreateTaskCommand createTaskCommand = new CreateTaskCommand(list.id(),
                title, description, tags, deadline, projectName, priority);
        Task savedTask = createTaskInPort.createTask(createTaskCommand);

        Task newTask = new Task.Builder(title).id(savedTask.id()).description(description)
                .tags(tags).deadline(deadline).projectName(projectName)
                .priority(priority).build();

        ToDoList savedList = loadListByIdOutPort.loadListById(list.id());
        List<Task> savedTasks = savedList.tasks();

        assertThat(savedTasks).containsExactly(newTask);
    }
}
