package ttsw.filopl.todoapp.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttsw.filopl.todoapp.model.Task;
import ttsw.filopl.todoapp.model.TaskRepository;

/**
 * Created by T. Filo Zegarlicki on 27.08.2022
 **/

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from tasks where id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);
}
