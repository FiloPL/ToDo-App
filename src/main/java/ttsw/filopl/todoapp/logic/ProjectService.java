package ttsw.filopl.todoapp.logic;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
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
@RequestScope
public class ProjectService {
    ProjectRepository projectRepository;
    TaskGroupRepository taskGroupRepository;
    TaskConfigurationProperties config;
    TaskGroupService taskGroupService;

    public ProjectService(final ProjectRepository projectRepository, final TaskGroupRepository taskGroupRepository, final TaskConfigurationProperties config, final TaskGroupService taskGroupService) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
        this.taskGroupService = taskGroupService;
    }

    // create project
    Project createProject(Project projectToSave) {
        return projectRepository.save(projectToSave);
    }

    // read all projects
    List<Project> readAllProjects() {
        return projectRepository.findAll();
    }

    // create group
    public GroupReadModel createGroup(int projectId, LocalDateTime deadline) {
        if (config.getTemplate().isAllowMultipleTaskFromTemplate() == false || taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalArgumentException("Something go wrong!");
        }
        GroupReadModel result = projectRepository.findById(projectId).map(project -> {
            var group = taskGroupService.createGroup(new GroupWriteModel());
            group.setDescription(project.getDescription());

            group.setTasks(project.getSteps().stream().map(projectStep -> {
                var task = new Task(projectStep.getDescription(), deadline.plusDays(projectStep.getDaysToDeadLine()));
                return new GroupTaskReadModel(task);
            }).collect(Collectors.toSet()));
            return group;
        }).orElseThrow(() -> new IllegalArgumentException("Project with given id is not found!"));
        return result;
    }
}

