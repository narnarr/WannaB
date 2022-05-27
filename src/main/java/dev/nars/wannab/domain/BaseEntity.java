package dev.nars.wannab.domain;

import dev.nars.wannab.check.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public class BaseEntity {
    @Column(columnDefinition = "timestamp not null default current_timestamp")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "timestamp not null default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10) not null default 'ACTIVE'")
    private Status statusCk;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.statusCk = Status.ACTIVE;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
