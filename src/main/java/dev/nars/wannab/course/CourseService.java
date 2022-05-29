package dev.nars.wannab.course;

import dev.nars.wannab.check.AgeGroup;
import dev.nars.wannab.check.BodyPart;
import dev.nars.wannab.check.Level;
import dev.nars.wannab.check.Sex;
import dev.nars.wannab.domain.Course;
import dev.nars.wannab.util.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static dev.nars.wannab.util.CustomResponseStatus.COURSE_NOT_FOUND;
import static dev.nars.wannab.util.CustomResponseStatus.DATABASE_ERROR;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // 모든 클래스 반환
    public List<Course> allCourse() throws CustomException {
        try {
            return courseRepository.findAll();
        } catch (Exception e) {
            throw new CustomException(DATABASE_ERROR);
        }

    }

    // 클래스 검색 결과 반환
    public List<Course> searchCourseByKeyword(String keyword) throws CustomException {
        try {
            return courseRepository.findWithKeyword(keyword);
        } catch (Exception e) {
            throw new CustomException(DATABASE_ERROR);
        }
    }

    // 클래스 필터링
    public Stream<Course> filterCourse(Level level, Sex targetSex, AgeGroup targetAge, BodyPart targetPart) throws CustomException {
        try {
            return courseRepository.findWithFilter(level, targetSex, targetAge, targetPart);
        } catch (Exception e) {
            throw new CustomException(DATABASE_ERROR);
        }
    }

    // 특정 클래스 반환
    public Course findCourse(Long courseId) throws CustomException {
        try {
            Optional<Course> course = courseRepository.findById(courseId);
            return course.orElseThrow(() -> new CustomException(COURSE_NOT_FOUND));
        } catch (Exception e) {
            throw new CustomException(DATABASE_ERROR);
        }
    }
}
