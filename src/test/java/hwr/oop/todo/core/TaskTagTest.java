package hwr.oop.todo.core;

import org.junit.jupiter.api.Test;

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
        TaskTag tag1 = new TaskTag("sample-tag");
        TaskTag tag2 = new TaskTag("sample-tag");
        int hash1 = tag1.hashCode();
        int hash2 = tag2.hashCode();

        assertThat(hash1).isEqualTo(hash2);
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
