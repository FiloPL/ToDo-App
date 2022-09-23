package ttsw.filopl.todoapp.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ttsw.filopl.todoapp.model.TaskGroup;
import ttsw.filopl.todoapp.model.TaskGroupRepository;

import java.util.List;

/**
 * Created by T. Filo Zegarlicki on 21.09.2022
 **/

@Repository
interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {

    @Override
    @Query("from TaskGroup g join fetch g.tasks")
    List<TaskGroup> findAll();

    @Override
    boolean existsByDoneIsFalseAndProject_Id(Integer projectId);
}
