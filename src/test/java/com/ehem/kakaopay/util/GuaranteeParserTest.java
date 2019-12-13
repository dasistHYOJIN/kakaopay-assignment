package com.ehem.kakaopay.util;

import com.ehem.kakaopay.model.guarantee.domain.Guarantee;
import com.ehem.kakaopay.model.guarantee.domain.vo.Amount;
import com.ehem.kakaopay.model.guarantee.domain.vo.Month;
import com.ehem.kakaopay.model.guarantee.domain.vo.Year;
import com.ehem.kakaopay.model.institute.domain.vo.InstituteType;
import com.ehem.kakaopay.util.vo.Record;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GuaranteeParserTest {
    @Test
    void Guarantee_리스트로_파싱_테스트() {
        // given
        List<Record> records = new ArrayList<>();
        records.add(new Record("연도,월,주택도시기금1)(억원),국민은행(억원),우리은행(억원),신한은행(억원),한국시티은행(억원),하나은행(억원),농협은행/수협은행(억원),외환은행(억원),기타은행(억원),,,,,,,"));
        records.add(new Record("2005,1,1019,846,82,95,30,157,57,80,99,,,,,,,"));
        records.add(new Record("2005,2,1144,864,91,97,35,168,36,111,114,,,,,,,"));
        records.add(new Record("2006,7,1113,406,470,57,15,225,136,102,88,,,,,,,"));
        records.add(new Record("2006,8,1390,508,260,59,22,303,161,93,109,,,,,,,"));
        records.add(new Record("2007,11,3026,1328,511,163,5,208,413,108,86,,,,,,,"));
        records.add(new Record("2010,3,4762,1926,1555,60,1,544,834,1115,161,,,,,,,"));

        // when
        List<Guarantee> guarantees = GuaranteeParser.parseToGuarantees(records);
        Guarantee guarantee = guarantees.get(0);

        // then
        assertThat(guarantees).hasSize(54);
        assertThat(guarantee.getYear()).isSameAs(Year.of(2005));
        assertThat(guarantee.getMonth()).isSameAs(Month.JAN);
        assertThat(guarantee.getInstitute().getInstituteType()).isSameAs(InstituteType.JOOTAEK);
        assertThat(guarantee.getAmount()).isEqualTo(Amount.of(1019));
    }
}