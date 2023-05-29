package hwr.oop.todo.application;

import hwr.oop.todo.core.TaskTag;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTagTest {
    @Test
    void testGetTitle() {
        TaskTag myTag = new TaskTag("sample-tag");
        String title = myTag.title();
        assertThat(title).isEqualTo("sample-tag");
    }
    @Test
    void testHashCode() {
        TaskTag myTag = new TaskTag("sample-tag");
        int hash = myTag.hashCode();
        assertThat(hash).isEqualTo(Objects.hash("sample-tag"));
    }

    @Test
    void testEqualObject() {
        TaskTag myTag = new TaskTag("sample-tag");
        TaskTag yourTag = new TaskTag("sample-tag");
        boolean c1 = myTag.equals(yourTag);
        assertThat(c1).isTrue();
        TaskTag thirdTag = new TaskTag("other-tag");
        boolean c2 = myTag.equals(thirdTag);
        assertThat(c2).isFalse();
        boolean c3 = myTag.equals(null);
        assertThat(c3).isFalse();

    }
}
