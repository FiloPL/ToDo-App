package ttsw.filopl.todoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Created by T. Filo Zegarlicki on 21.09.2022
 **/

@Entity
@Table(name = "project_steps")
public class ProjectSteps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Project steps shouldnt`t be empty or null")
    private String description;

    private int daysToDeadLine;

    @ManyToOne
    //@JoinColumn(name = "project_id");
    private Project project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDaysToDeadLine() {
        return daysToDeadLine;
    }

    public void setDaysToDeadLine(int daysToDeadLine) {
        this.daysToDeadLine = daysToDeadLine;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
