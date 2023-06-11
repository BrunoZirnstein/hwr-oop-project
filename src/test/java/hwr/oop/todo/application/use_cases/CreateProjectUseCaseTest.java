package hwr.oop.todo.application.use_cases;

import hwr.oop.todo.application.ports.in.CreateProjectInPort;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;
import hwr.oop.todo.core.Project;
import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.persistence.PersistenceAdapter;
import hwr.oop.todo.persistence.csv.CSVHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static hwr.oop.todo.application.ports.in.CreateProjectInPort.CreateProjectCommand;
import static org.assertj.core.api.Assertions.assertThat;

class CreateProjectUseCaseTest {
    PersistenceAdapter persistenceAdapter = new CSVHandler();
    LoadListByIdOutPort loadListByIdOutPort = persistenceAdapter;
    OverwriteListOutPort overwriteListOutPort = persistenceAdapter;

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
    @DisplayName("Create project")
    void createProject() {
        String title = "university";
        LocalDate deadline = LocalDate.of(2023, 6, 20);

        CreateProjectCommand createProjectCommand = new CreateProjectCommand(
                list.id(), title, deadline);
        Project createdProject = createProjectInPort.createProject(createProjectCommand);

        Project newProject = new Project.Builder(title).id(createdProject.id()).deadline(
                deadline).build();

        ToDoList savedList = loadListByIdOutPort.loadListById(list.id());
        List<Project> savedProjects = savedList.projects();

        assertThat(savedProjects).containsExactly(newProject);
    }
}
