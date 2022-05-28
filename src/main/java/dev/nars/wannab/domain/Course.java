package dev.nars.wannab.domain;

import dev.nars.wannab.check.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Course extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @Column(length = 45, nullable = false)
    private String title;

    @Column(length = 255, nullable = true)
    private String description;

    @Column(columnDefinition = "int default 0", nullable = false)
    private int likeCnt;

    @Column(columnDefinition = "text", nullable = false)
    private String thumbnailImg;

    @Enumerated(EnumType.STRING)
    @Column(name = "level_ck", length = 10, nullable = false)
    private Level level;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_age_ck", columnDefinition = "varchar(10) default 'ALL'", nullable = false)
    private AgeGroup targetAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_sex_ck", columnDefinition = "varchar(10) default 'ALL'", nullable = false)
    private Sex targetSex;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_part_ck", columnDefinition = "varchar(10) default 'ALL'", nullable = false)
    private BodyPart targetPart;

    // 1~7 days, 1~12 months
    @Column(name = "duration_num_ck", columnDefinition = "int default 1", nullable = false)
    private int durationNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration_type_ck", columnDefinition = "varchar(10) default 'WEEK'", nullable = false)
    private DurationType durationType;

    @Builder
    public Course(Long id, Mentor mentor, String title, String description, int likeCnt, String thumbnailImg, Level level, AgeGroup targetAge, Sex targetSex, BodyPart targetPart, int durationNum, DurationType durationType) {
        this.id = id;
        this.mentor = mentor;
        this.title = title;
        this.description = description;
        this.likeCnt = likeCnt;
        this.thumbnailImg = thumbnailImg;
        this.level = level;
        this.targetAge = targetAge;
        this.targetSex = targetSex;
        this.targetPart = targetPart;
        this.durationNum = durationNum;
        this.durationType = durationType;
    }
}
