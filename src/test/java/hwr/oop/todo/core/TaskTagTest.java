package hwr.oop.todo.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskTagTest {
    @Test
    void testGetTitle() {
        TaskTag tag = new TaskTag("sample-tag");
        String title = tag.title();
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
        TaskTag tag = new TaskTag("sample-tag");
        TaskTag sameTag = new TaskTag("sample-tag");

        assertThat(tag).isEqualTo(sameTag);

        TaskTag differentTag = new TaskTag("other-tag");
        assertThat(tag).isNotEqualTo(differentTag);

    }
}
