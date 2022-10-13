package ttsw.filopl.todoapp.model;

import java.util.List;
import java.util.Optional;

/**
 * Created by T. Filo Zegarlicki on 21.09.2022
 **/

public interface TaskGroupRepository {
    List<TaskGroup> findAll();

    Optional<TaskGroup> findById(Integer id);

    TaskGroup save(TaskGroup entity);

    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
