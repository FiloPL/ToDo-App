package ttsw.filopl.todoapp.logic;

import org.junit.jupiter.api.Test;
import ttsw.filopl.todoapp.model.TaskGroupRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by T. Filo Zegarlicki on 05.10.2022
 **/

class TaskGroupServiceTest {

    @Test
    void createGroup() {
    }

    @Test
    void readAll() {
    }

    @Test
    void toggleGroup() {

    }

    private TaskGroupRepository groupRepositoryReturning(final boolean result) {
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(result);

        return mockGroupRepository;
    }
}