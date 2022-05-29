package dev.nars.wannab.course.dto;

import dev.nars.wannab.check.*;
import dev.nars.wannab.domain.Course;
import dev.nars.wannab.domain.Mentor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class GetCourseDto {
    private Long id;
    private String title;
    private String thumbnailImg;
    private int likeCnt;
    private String description;
    private Level level;
    private AgeGroup targetAge;
    private Sex targetSex;
    private BodyPart targetPart;
    private int durationNum;
    private DurationType durationType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Mentor mentor;

    public static GetCourseDto from(Course course) {
        GetCourseDto dto = new GetCourseDto();
        dto.id = course.getId();
        dto.title = course.getTitle();
        dto.thumbnailImg = course.getThumbnailImg();
        dto.likeCnt = course.getLikeCnt();
        dto.description = course.getDescription();
        dto.level = course.getLevel();
        dto.targetAge = course.getTargetAge();
        dto.targetSex = course.getTargetSex();
        dto.targetPart = course.getTargetPart();
        dto.durationNum = course.getDurationNum();
        dto.durationType = course.getDurationType();
        dto.createdAt = course.getCreatedAt();
        dto.updatedAt = course.getUpdatedAt();
        dto.mentor = course.getMentor();

        return dto;
    }

}
