package ttsw.filopl.todoapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ttsw.filopl.todoapp.model.Task;
import ttsw.filopl.todoapp.model.TaskRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ttsw.filopl.todoapp.TaskConfigurationProperties;
import ttsw.filopl.todoapp.model.*;
import ttsw.filopl.todoapp.model.projection.GroupReadModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by T. Filo Zegarlicki on 14.10.2022
 **/


@WebMvcTest(TaskController.class)
class TaskControllerLightIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository repo;

    @Test
    void httpGet_returnsGivenTask() throws Exception {
        // given
        String description = "foo";
        when(repo.findById(anyInt()))
                .thenReturn(Optional.of(new Task("foo", LocalDateTime.now())));

        // when + then

        mockMvc.perform(get("/tasks/123"))
                .andDo(print())
                .andExpect((ResultMatcher) content().string(containsString(description)));
    }
}
