package dev.nars.wannab.course;

import dev.nars.wannab.check.AgeGroup;
import dev.nars.wannab.check.BodyPart;
import dev.nars.wannab.check.Level;
import dev.nars.wannab.check.Sex;
import dev.nars.wannab.course.dto.GetCourseDto;
import dev.nars.wannab.domain.Course;
import dev.nars.wannab.util.CustomException;
import dev.nars.wannab.util.CustomResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 클래스 추천 API
     * 사용자가 가장 최근에 수강한 클래스와 목표 운동부위가 동일한 클래스 5개를 랜덤으로 추천해준다.
     */

    /**
     * 클래스 검색 API
     * 검색 키워드가 클래스명, 멘토명 중에 하나라도 포함된다면 해당 클래스 모두 반환
     */
    @GetMapping("/courses/search")
    public CustomResponse<List<GetCourseDto>> courseSearch(@RequestParam(required = false) String keyword) {
        try {
            List<GetCourseDto> searchList;
            if(keyword == null) {
                searchList = courseService.allCourse().stream().map(GetCourseDto::from).collect(Collectors.toList());
            } else {
                searchList = courseService.searchCourseByKeyword(keyword).stream().map(GetCourseDto::from).collect(Collectors.toList());
            }
            return new CustomResponse<>(searchList);
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 클래스 필터링 API
     * 레벨, 성별, 나이대, 운동부위로 필터링 가능
     */
    @GetMapping("/courses/filter")
    public CustomResponse<List<GetCourseDto>> courseFilter(@RequestParam(defaultValue = "ALL") Level level, @RequestParam(defaultValue = "ALL") Sex targetSex,
                                           @RequestParam(defaultValue = "ALL") AgeGroup targetAge, @RequestParam(defaultValue = "ALL") BodyPart targetPart) {
        try {
            List<GetCourseDto> filterList = courseService.filterCourse(level, targetSex, targetAge, targetPart).map(GetCourseDto::from).collect(Collectors.toList());
            return new CustomResponse<>(filterList);
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 클래스 상세보기 API
     */
    @GetMapping("/course/{courseId}")
    public CustomResponse<GetCourseDto> courseDetails(@PathVariable Long courseId) {
        try {
            Course course = courseService.findCourse(courseId);
            return new CustomResponse<>(GetCourseDto.from(course));
        } catch (CustomException exception) {
            return new CustomResponse<>(exception.getResponseStatus());
        }
    }

    /**
     * 클래스 찜하기 API
     */



    /**
     * 클래스 수강 신청 API
     */

    /**
     * 찜한 클래스 GET API
     */


    /**
     * 수강 완료 클래스 GET API
     */

}
