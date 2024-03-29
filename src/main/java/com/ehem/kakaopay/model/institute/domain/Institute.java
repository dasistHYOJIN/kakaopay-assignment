package com.ehem.kakaopay.model.institute.domain;

import com.ehem.kakaopay.model.institute.domain.vo.InstituteType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@EqualsAndHashCode
public class Institute {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private InstituteType instituteType;

    public Institute(final InstituteType instituteType) {
        this.instituteType = instituteType;
    }

    public String getCode() {
        return instituteType.getCode();
    }

    public String getName() {
        return instituteType.getName();
    }

}
