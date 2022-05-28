package dev.nars.wannab.domain;

import dev.nars.wannab.domain.embed.BankAccount;
import dev.nars.wannab.domain.embed.Contact;
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
    private String nickname;

    @Column(columnDefinition = "text", nullable = false)
    private String profileImg;

    @Embedded
    private Contact contact;

    @Embedded
    private BankAccount bankAccount;

    @Builder
    public Mentor(Long id, String nickname, String profileImg, Contact contact, BankAccount bankAccount) {
        this.id = id;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.contact = contact;
        this.bankAccount = bankAccount;
    }
}
