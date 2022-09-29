package ttsw.filopl.todoapp.logic;

import org.springframework.stereotype.Service;
import ttsw.filopl.todoapp.TaskConfigurationProperties;
import ttsw.filopl.todoapp.model.*;
import ttsw.filopl.todoapp.model.projection.GroupReadModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by T. Filo Zegarlicki on 24.09.2022
 **/

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties config;

    public ProjectService(final ProjectRepository projectRepository, final
    TaskGroupRepository taskGroupRepository, final TaskConfigurationProperties config) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
    }

    // create project
    public Project save(Project projectToSave) {
        return projectRepository.save(projectToSave);
    }

    // read all projects
    public List<Project> readAllProjects() {
        return projectRepository.findAll();
    }

    // create group
    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {
        if (!config.getTemplate().isAllowMultipleTaskFromTemplate() &&
                taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed!");
        }

        TaskGroup result = projectRepository.findById(projectId)
                .map(project -> {
                    var targetGroup = new TaskGroup();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                            project.getSteps().stream()
                                    .map(projectSteps -> new Task(
                                            projectSteps.getDescription(),
                                            deadline.plusDays(projectSteps.getDaysToDeadLine()))
                                    ).collect(Collectors.toSet())
                    );
                    return targetGroup;
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id is not found!"));
        return new GroupReadModel(result);
    }
}