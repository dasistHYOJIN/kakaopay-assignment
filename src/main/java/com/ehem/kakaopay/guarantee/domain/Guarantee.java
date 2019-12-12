package com.ehem.kakaopay.guarantee.domain;

import com.ehem.kakaopay.guarantee.domain.vo.Amount;
import com.ehem.kakaopay.guarantee.domain.vo.Month;
import com.ehem.kakaopay.guarantee.domain.vo.MonthConverter;
import com.ehem.kakaopay.guarantee.domain.vo.Year;
import com.ehem.kakaopay.institute.domain.Institute;
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

    @Convert(converter = MonthConverter.class)
    @Column(nullable = false)
    private Month month;

    @AttributeOverride(
            name = "value",
            column = @Column(name = "amount", nullable = false))
    private Amount amount;

    @OneToOne(fetch = FetchType.LAZY)
    private Institute institute;

    @Builder
    public Guarantee(final Year year, final Month month, final Amount amount, final Institute institute) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.institute = institute;
    }
}
