package hwr.oop.todo.application.use_cases;

import hwr.oop.todo.application.ports.in.CreateTaskInPort;
import hwr.oop.todo.application.ports.in.CreateTaskInPort.CreateTaskCommand;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.application.ports.out.OverwriteListOutPort;
import hwr.oop.todo.core.*;
import hwr.oop.todo.persistence.PersistenceAdapter;
import hwr.oop.todo.persistence.csv.CSVHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CreateTaskUseCaseTest {
    PersistenceAdapter persistenceAdapter = new CSVHandler();
    LoadListByIdOutPort loadListByIdOutPort = persistenceAdapter;
    CreateTaskInPort createTaskInPort = new CreateTaskUseCase(
            persistenceAdapter, persistenceAdapter);
    ToDoList list;

    @Test
    @DisplayName("Create task")
    void createTask() {
        ToDoList list = new ToDoList("Bruno");
        this.list = list;
        OverwriteListOutPort overwriteListOutPort = persistenceAdapter;
        overwriteListOutPort.overwriteList(list);

        Task newTask = new Task.Builder("Water plants").description(
                        "Water all the plants in the living room and in the bedroom.")
                .tags(List.of(new TaskTag("home")))
                .deadline(LocalDate.of(2023, 5, 30)).priority(TaskPriority.HIGH)
                .status(TaskStatus.IN_PROGRESS).build();

        CreateTaskCommand createTaskCommand = new CreateTaskCommand(list.id(),
                newTask);
        createTaskInPort.createTask(createTaskCommand);

        ToDoList savedList = loadListByIdOutPort.loadListById(list.id());
        List<Task> savedTasks = savedList.tasks();

        assertThat(savedTasks.get(0)).hasToString(newTask.toString());
    }
}
