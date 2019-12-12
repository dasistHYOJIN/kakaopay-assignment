package com.ehem.kakaopay.guarantee.domain;

import com.ehem.kakaopay.guarantee.domain.vo.Amount;
import com.ehem.kakaopay.guarantee.domain.vo.Month;
import com.ehem.kakaopay.guarantee.domain.vo.Year;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@EqualsAndHashCode(of = "id")
public class Guarantee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(
            name = "value",
            column = @Column(name = "year", nullable = false))
    private Year year;

    @AttributeOverride(
            name = "value",
            column = @Column(name = "month", nullable = false))
    private Month month;

    @AttributeOverride(
            name = "value",
            column = @Column(name = "amount", nullable = false))
    private Amount amount;

    @Builder
    public Guarantee(final Year year, final Month month, final Amount amount) {
        this.year = year;
        this.month = month;
        this.amount = amount;
    }
}
