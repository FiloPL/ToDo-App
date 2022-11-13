package ttsw.filopl.todoapp.model.projection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ttsw.filopl.todoapp.model.Task;
import ttsw.filopl.todoapp.model.TaskGroup;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by T. Filo Zegarlicki on 13.11.2022
 **/

class GroupReadModelTest {

    @Test
    @DisplayName("should create null deadline for group when no task deadlines")
    void constructor_noDeadlines_createsNullDeadline() {
        // given
        var source = new TaskGroup();
        source.setDescription("foo");
        source.setTasks(Set.of(new Task("bar", null)));

        // when
        var result = new GroupReadModel(source);

        // then
        assertThat(result).hasFieldOrPropertyWithValue("deadline", null);
    }
}