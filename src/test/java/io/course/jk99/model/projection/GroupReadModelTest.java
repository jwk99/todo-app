package io.course.jk99.model.projection;

import io.course.jk99.model.Task;
import io.course.jk99.model.TaskGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GroupReadModelTest {

    @Test
    @DisplayName("should create null deadline for group when no deadlines")
    void constructor_noDeadlines_createsNullDeadline()
    {
        //given
        var source = new TaskGroup();
        source.setDescription("foo");
        source.setTasks(Set.of(new Task("bar", null)));

        //when
        var result = new GroupReadModel(source);

        //then
        assertThat(result).hasFieldOrPropertyWithValue("deadline", null);
    }

}