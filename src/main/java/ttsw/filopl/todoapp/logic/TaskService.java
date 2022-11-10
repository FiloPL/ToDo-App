package ttsw.filopl.todoapp.logic;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ttsw.filopl.todoapp.model.Task;
import ttsw.filopl.todoapp.model.TaskRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by T. Filo Zegarlicki on 04.11.2022
 **/

@Service
public class TaskService {

    private final TaskRepository repository;

    TaskService(final TaskRepository repository) {
        this.repository = repository;
    }

    @Async
    public CompletableFuture<List<Task>> findAllAsync() {
        return CompletableFuture.supplyAsync(repository::findAll);
    }

}
