package ttsw.filopl.todoapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import ttsw.filopl.todoapp.model.Task;
import ttsw.filopl.todoapp.model.TaskRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by T. Filo Zegarlicki on 14.10.2022
 **/

@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    TaskRepository repo;

    @Test
    void httpGet_returnAllTasks() {
        // given
        repo.save(new Task("foo", LocalDateTime.now()));
        repo.save(new Task("bar", LocalDateTime.now()));

        // when
        Task[] result = restTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);

        // then
        assertThat(result).hasSize(2);
    }
}