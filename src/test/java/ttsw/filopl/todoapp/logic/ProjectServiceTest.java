package ttsw.filopl.todoapp.logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ttsw.filopl.todoapp.TaskConfigurationProperties;
import ttsw.filopl.todoapp.model.TaskGroup;
import ttsw.filopl.todoapp.model.TaskGroupRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by T. Filo Zegarlicki on 26.09.2022
 **/

class ProjectServiceTest {


    @Test
    void save() {
    }

    @Test
    void readAllProjects() {
    }

    @Test
    void createGroup_existGroup() {
        //given
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(true);

        //when

        //then
        assertTrue(mockGroupRepository.existsByDoneIsFalseAndProject_Id(500));
    }

    @Test
    @DisplayName("shlout throw IllegalStateException ")
    void createGroup_noMultiGroupsConfig_And_undoneGroupExists_throwsIllegalStateException() {
        // given
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(true);

        var mockTemplate = mock(TaskConfigurationProperties.Template.class);
        when(mockTemplate.isAllowMultipleTaskFromTemplate()).thenReturn(false);

        var mockConfig = mock(TaskConfigurationProperties.class);
        when(mockConfig.getTemplate()).thenReturn(mockTemplate);

        // system under test
        var toTest = new ProjectService(null, mockGroupRepository, mockConfig);

        // when
        var exception = catchThrowable(
                () -> toTest.createGroup(LocalDateTime.now(), 0)
        );
        // then

        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("one undone group");
    }
}