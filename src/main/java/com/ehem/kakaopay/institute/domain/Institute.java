package com.ehem.kakaopay.institute.domain;

import com.ehem.kakaopay.institute.domain.vo.InstituteType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@EqualsAndHashCode
public class Institute {

    @Id
    @Enumerated(EnumType.STRING)
    private InstituteType instituteType;

    public Institute(final InstituteType instituteType) {
        this.instituteType = instituteType;
    }

    public String getCode() {
        return instituteType.getCode();
    }

    public String getName() {
        return instituteType.getNames()[0]; // TODO: 2019-12-12 여기 어떻게 할 지 고민 좀
    }


}
