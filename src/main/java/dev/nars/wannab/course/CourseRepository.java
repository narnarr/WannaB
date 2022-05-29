package dev.nars.wannab.course;

import dev.nars.wannab.check.AgeGroup;
import dev.nars.wannab.check.BodyPart;
import dev.nars.wannab.check.Level;
import dev.nars.wannab.check.Sex;
import dev.nars.wannab.domain.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class CourseRepository {

    private final EntityManager em;

    public CourseRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Course> findById(Long courseId) {
        Course course = em.find(Course.class, courseId);
        return Optional.ofNullable(course);
    }

    public List<Course> findAll() {
        String sql = "select c from Course c order by c.likeCnt desc";
        return em.createQuery(sql).getResultList();
    }

    public List<Course> findWithKeyword(String keyword) {
        String sql = "select c from Course c inner join Mentor m on c.mentor=m " +
                "where c.statusCk='ACTIVE' and (c.mentor.nickname like :keyword or c.title like :keyword) order by c.likeCnt desc";
        return em.createQuery(sql).setParameter("keyword", "%"+keyword+"%").getResultList();
    }

    public Stream<Course> findWithFilter(Level level, Sex sex, AgeGroup group, BodyPart part) {
        StringBuilder sql = new StringBuilder();
        sql.append("select c from Course c where c.statusCk='ACTIVE'");
        if(level!=Level.ALL) sql.append(" and c.level=:level");
        if(sex!=Sex.ALL) sql.append(" and c.targetSex=:sex");
        if(group!=AgeGroup.ALL) sql.append(" and c.targetAge=:group");
        if(part!=BodyPart.ALL) sql.append(" and c.targetPart=:part");

        Query query = em.createQuery(sql.toString());
        if(level!=Level.ALL) query.setParameter("level", level);
        if(sex!=Sex.ALL) query.setParameter("sex", sex);
        if(group!=AgeGroup.ALL) query.setParameter("group", group);
        if(part!=BodyPart.ALL) query.setParameter("part", part);

        return query.getResultStream();
    }
}
