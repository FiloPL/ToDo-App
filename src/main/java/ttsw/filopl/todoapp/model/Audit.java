package ttsw.filopl.todoapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by T. Filo Zegarlicki on 08.09.2022
 **/

@Embeddable
class Audit {

    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @PrePersist
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preMerge() {
        updatedOn = LocalDateTime.now();
    }
}
