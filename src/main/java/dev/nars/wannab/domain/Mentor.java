package dev.nars.wannab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Mentor extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentor_id", nullable = false)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Column(length = 20, nullable = false)
    private String accountBank;

    @Column(length = 20, nullable = false)
    private String accountNumber;

    @Column(columnDefinition = "text", nullable = false)
    private String profileImg;

    @Builder
    public Mentor(Long id, String name, String email, String nickname, String phoneNumber, String accountBank, String accountNumber, String profileImg) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.accountBank = accountBank;
        this.accountNumber = accountNumber;
        this.profileImg = profileImg;
    }
}
