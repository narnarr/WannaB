package dev.nars.wannab.domain;

import dev.nars.wannab.check.Sex;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(nullable = true)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex_ck", length = 10, nullable = true)
    private Sex sex;

    @Column(columnDefinition = "text", nullable = true)
    private String profileImg;

    @Builder
    public User(Long id, String email, String password, String nickname, LocalDate birthday, Sex sex, String profileImg) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
        this.sex = sex;
        this.profileImg = profileImg;
    }
}
