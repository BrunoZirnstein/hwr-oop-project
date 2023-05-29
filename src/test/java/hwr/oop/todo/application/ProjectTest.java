package hwr.oop.todo.application;

import hwr.oop.todo.core.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectTest {
    @Test
    void testProject() {
        Assertions.assertDoesNotThrow(() -> new Project("Test", LocalDate.of(2000, 1, 1)));
        Project project = new Project("Test", LocalDate.of(2000, 1, 1));
        assertThat(project.title()).isEqualTo("Test");
    }

    @Test
    void testDeadline() {
        Project project = new Project("Test", LocalDate.of(2000, 1, 1));
        assertThat(project.deadline()).isEqualTo(LocalDate.of(2000, 1, 1));
        LocalDate date = LocalDate.of(2024, 4, 4);
        project.setDeadline(date);
        assertThat(date).isEqualTo(project.deadline());
    }
}