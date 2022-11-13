package ttsw.filopl.todoapp.model.projection;

import org.springframework.format.annotation.DateTimeFormat;
import ttsw.filopl.todoapp.model.Task;
import ttsw.filopl.todoapp.model.TaskGroup;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Created by T. Filo Zegarlicki on 23.09.2022
 **/

public class GroupTaskWriteModel {

    @NotBlank(message = "Task's description must not be empty")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadline;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(final LocalDateTime deadline) {
        this.deadline = deadline;
    }

    Task toTask(final TaskGroup group) {
        return new Task(description, deadline, group);
    }
}
