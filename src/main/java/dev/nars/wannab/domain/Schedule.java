package dev.nars.wannab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @Column(length = 20, nullable = false)
    private String days; // 1 ~ 1,2,3,4,5,6,7

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private boolean onAlarm;


    @Builder
    public Schedule(Long id, User user, Routine routine, String days, LocalTime startTime, LocalDate startDate, boolean onAlarm) {
        this.id = id;
        this.user = user;
        this.routine = routine;
        this.days = days;
        this.startTime = startTime;
        this.startDate = startDate;
        this.onAlarm = onAlarm;
    }
}
