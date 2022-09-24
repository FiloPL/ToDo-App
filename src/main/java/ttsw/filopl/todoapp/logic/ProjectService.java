package ttsw.filopl.todoapp.logic;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import ttsw.filopl.todoapp.TaskConfigurationProperties;
import ttsw.filopl.todoapp.model.ProjectRepository;
import ttsw.filopl.todoapp.model.TaskGroupRepository;

/**
 * Created by T. Filo Zegarlicki on 24.09.2022
 **/

@Service
@RequestScope
public class ProjectService {
    private ProjectRepository projectRepository;
    private TaskGroupRepository repository;
    private TaskConfigurationProperties config;

    ProjectService(final ProjectRepository projectRepository, final TaskGroupRepository repository, final TaskConfigurationProperties config) {
        this.projectRepository = projectRepository;
        this.repository = repository;
        this.config = config;
    }

}
