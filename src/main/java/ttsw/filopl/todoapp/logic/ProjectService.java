package ttsw.filopl.todoapp.logic;

import org.springframework.stereotype.Service;
import ttsw.filopl.todoapp.TaskConfigurationProperties;
import ttsw.filopl.todoapp.model.Project;
import ttsw.filopl.todoapp.model.ProjectRepository;
import ttsw.filopl.todoapp.model.Task;
import ttsw.filopl.todoapp.model.TaskGroupRepository;
import ttsw.filopl.todoapp.model.projection.GroupReadModel;
import ttsw.filopl.todoapp.model.projection.GroupTaskReadModel;
import ttsw.filopl.todoapp.model.projection.GroupWriteModel;

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
    private TaskGroupService taskGroupService;


    public ProjectService(final ProjectRepository projectRepository, final
    TaskGroupRepository taskGroupRepository, final TaskConfigurationProperties config) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
    }

    public ProjectService(final ProjectRepository projectRepository, final TaskGroupRepository taskGroupRepository, final TaskConfigurationProperties config, final TaskGroupService taskGroupService) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
        this.taskGroupService = taskGroupService;
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
        if (config.getTemplate().isAllowMultipleTaskFromTemplate() == false
                || taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed!");
        }
        GroupReadModel result = projectRepository.findById(projectId).map(project -> {
            var targetGroup = taskGroupService.createGroup(new GroupWriteModel());
            targetGroup.setDescription(project.getDescription());

            targetGroup.setTasks(project.getSteps().stream().map(projectStep -> {
                var task = new Task(projectStep.getDescription(), deadline.plusDays(projectStep.getDaysToDeadLine()));
                return new GroupTaskReadModel(task);
            }).collect(Collectors.toSet()));
            return targetGroup;
        }).orElseThrow(() -> new IllegalArgumentException("Project with given id is not found!"));
        return result;
    }
}