package dev.nars.wannab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Routine extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(columnDefinition = "int default 1", nullable = false)
    private int perWeek;

    @Column(columnDefinition = "int default 1", nullable = false)
    private int perDay;

    @Column(columnDefinition = "text", nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private int videoLength;


    @Builder
    public Routine(Long id, Course course, int perWeek, int perDay, String videoUrl, int videoLength) {
        this.id = id;
        this.course = course;
        this.perWeek = perWeek;
        this.perDay = perDay;
        this.videoUrl = videoUrl;
        this.videoLength = videoLength;
    }
}
