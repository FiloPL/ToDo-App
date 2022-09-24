package ttsw.filopl.todoapp.logic;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
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

@Service
@RequestScope
public class TaskGroupService {

    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    TaskGroupService(final TaskGroupRepository repository, final TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup(GroupWriteModel source){
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll() {
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId) {
        if(repository.existsByDoneIsFalseAndProject_Id(groupId)) {
            throw new IllegalStateException("group have undone task");
        }

        TaskGroup result = repository.findById(groupId).
                orElseThrow( () -> new IllegalStateException( "Task Group with given ID not found"));
        result.setDone(!result.isDone());

    }

}
