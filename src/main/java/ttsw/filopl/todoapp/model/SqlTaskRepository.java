package ttsw.filopl.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by T. Filo Zegarlicki on 27.08.2022
 **/

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {

}
