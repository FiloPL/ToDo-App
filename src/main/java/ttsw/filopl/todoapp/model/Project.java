package ttsw.filopl.todoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Created by T. Filo Zegarlicki on 21.09.2022
 **/

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Project shouldnt`t be empty or null")
    private String description;

    @OneToMany(mappedBy = "project")
    Set<TaskGroup> groups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    Set<ProjectSteps> steps;

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    Set<TaskGroup> getGroups() {
        return groups;
    }

    void setGroups(Set<TaskGroup> groups) {
        this.groups = groups;
    }

    public Set<ProjectSteps> getSteps() {
        return steps;
    }

    void setSteps(Set<ProjectSteps> steps) {
        this.steps = steps;
    }
}
