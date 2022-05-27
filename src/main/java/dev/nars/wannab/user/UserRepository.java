package dev.nars.wannab.user;

import dev.nars.wannab.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepository {

    private final EntityManager em;

    @Autowired
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public User save(User user) {
        if(user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    public Optional<User> findByUserId(Long userId) {
        return em.createQuery("select u from User u where u.id=:userId")
                .setParameter("userId", userId).getResultList()
                .stream().findAny();
    }

    public Optional<User> findByEmail(String email) {
        List<User> user = em.createQuery("select u from User u where u.email=:email")
                .setParameter("email", email).getResultList();
        return user.stream().findAny();
    }
}
