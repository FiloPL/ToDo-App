package ttsw.filopl.todoapp.model;

import java.util.List;
import java.util.Optional;

/**
 * Created by T. Filo Zegarlicki on 21.09.2022
 **/

public interface ProjectRepository {
    List<Project> findAll();

    Optional<Project> findById(Integer id);

    Project save(Project entity);
}
