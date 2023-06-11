package hwr.oop.todo.application.use_cases;

import hwr.oop.todo.application.ports.in.CreateListInPort;
import hwr.oop.todo.application.ports.out.LoadListByIdOutPort;
import hwr.oop.todo.core.ToDoList;
import hwr.oop.todo.persistence.PersistenceAdapter;
import hwr.oop.todo.persistence.csv.CSVHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static hwr.oop.todo.application.ports.in.CreateListInPort.CreateListCommand;
import static org.assertj.core.api.Assertions.assertThat;

class CreateListUseCaseTest {
    PersistenceAdapter persistenceAdapter = new CSVHandler();
    LoadListByIdOutPort loadListByIdOutPort = persistenceAdapter;
    CreateListInPort createListInPort = new CreateListUseCase(
            persistenceAdapter);

    @Test
    @DisplayName("Create list")
    void createList() {
        String owner = "Bruno";
        CreateListCommand createListCommand = new CreateListCommand(owner);
        ToDoList createdList = createListInPort.createList(createListCommand);
        ToDoList newList = new ToDoList(createdList.id(), owner);

        ToDoList savedList = loadListByIdOutPort.loadListById(newList.id());

        assertThat(savedList).isEqualTo(newList);
    }
}
