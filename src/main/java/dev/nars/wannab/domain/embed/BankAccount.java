package dev.nars.wannab.domain.embed;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@JsonIgnoreType
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Column(length = 20, nullable = false)
    private String bank;

    @Column(length = 20, nullable = false)
    private String accountNumber;
}
