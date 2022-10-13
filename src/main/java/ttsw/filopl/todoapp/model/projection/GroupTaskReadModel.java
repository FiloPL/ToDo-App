package ttsw.filopl.todoapp.model.projection;

import ttsw.filopl.todoapp.model.Task;

import java.time.LocalDateTime;

/**
 * Created by T. Filo Zegarlicki on 23.09.2022
 **/

public class GroupTaskReadModel {
    private String description;
    private boolean done;

    GroupTaskReadModel(Task source) {
        description = source.getDescription();
        done = source.isDone();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }
}
