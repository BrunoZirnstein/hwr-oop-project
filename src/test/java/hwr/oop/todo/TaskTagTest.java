package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTagTest {
    @Test
    void testGetTitle() {
        TaskTag myTag = new TaskTag("sample-tag");
        String title = myTag.title();
        assertThat(title).isEqualTo("sample-tag");
        assertThat(myTag.hashCode()).isEqualTo(Objects.hash("sample-tag"));
    }
}
