package ttsw.filopl.todoapp.model.projection;

import ttsw.filopl.todoapp.model.Task;

import java.time.LocalDateTime;

/**
 * Created by T. Filo Zegarlicki on 23.09.2022
 **/

public class GroupTaskWriteModel {

    private String description;
    private LocalDateTime deadline;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Task toTask() {
        return new Task(description, deadline);

    }
}