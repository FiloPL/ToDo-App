package ttsw.filopl.todoapp.logic;

import ttsw.filopl.todoapp.model.Project;
import ttsw.filopl.todoapp.model.TaskGroup;
import ttsw.filopl.todoapp.model.TaskGroupRepository;
import ttsw.filopl.todoapp.model.TaskRepository;
import ttsw.filopl.todoapp.model.projection.GroupReadModel;
import ttsw.filopl.todoapp.model.projection.GroupWriteModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by T. Filo Zegarlicki on 21.09.2022
 **/

public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    TaskGroupService(final TaskGroupRepository repository, final TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup(final GroupWriteModel source) {
        return createGroup(source, null);
    }

    GroupReadModel createGroup(final GroupWriteModel source, final Project project) {
        TaskGroup result = repository.save(source.toGroup(project));
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll() {
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId) {
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)) {
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("TaskGroup with given id not found"));
        result.setDone(!result.isDone());
        repository.save(result);
    }
}
