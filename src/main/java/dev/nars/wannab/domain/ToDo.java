package dev.nars.wannab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ToDo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @Column(nullable = false)
    private LocalDate bookedAt;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean isFinished;


    @Builder
    public ToDo(Long id, User user, Routine routine, LocalDate bookedAt, boolean isFinished) {
        this.id = id;
        this.user = user;
        this.routine = routine;
        this.bookedAt = bookedAt;
        this.isFinished = isFinished;
    }
}
