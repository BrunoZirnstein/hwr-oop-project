package hwr.oop.todo.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ProjectTest {
    @Test
    @DisplayName("Creating new project")
    void newProject() {
        Assertions.assertDoesNotThrow(() -> new Project.Builder("Test").build());
        Project project = new Project.Builder("Test").build();
        assertThat(project.title()).isEqualTo("Test");
    }

    @Test
    @DisplayName("Changing title of project")
    void changeTitle_valid() {
        Project project = new Project.Builder("Test").build();
        project.setTitle("New title");
        String title = project.title();
        assertThat(title).contains("New title");
    }

    @Test
    @DisplayName("Changing title of project invalid")
    void changeTitle_invalid() {
        Project project = new Project.Builder("Test").build();
        assertThatThrownBy(() -> project.setTitle("")).isInstanceOf(InvalidParameterException.class);
    }
    @Test
    @DisplayName("Getting Id of project")
    void idOfProject() {
        Project project = new Project.Builder("Test").build();
        UUID id = project.id();
        assertThat(id).isInstanceOf(UUID.class);
    }

    @Test
    @DisplayName("Changing deadline of project")
    void deadlineOfProject() {
        Project project = new Project.Builder("Test").deadline(LocalDate.of(2000, 1, 1)).build();
        assertThat(project.deadline()).isEqualTo(LocalDate.of(2000, 1, 1));

        LocalDate date = LocalDate.of(2024, 4, 4);
        project.setDeadline(date);
        assertThat(date).isEqualTo(project.deadline());
    }

    @Test
    @DisplayName("Changing deadlinge of project invalid")
    void changeDeadline_invalid() {
        Project project = new Project.Builder("Test").deadline(LocalDate.of(2000, 1, 1)).build();
        assertThatThrownBy(() -> project.setDeadline(LocalDate.of(0,0,0))).isInstanceOf(DateTimeException.class);
    }
}