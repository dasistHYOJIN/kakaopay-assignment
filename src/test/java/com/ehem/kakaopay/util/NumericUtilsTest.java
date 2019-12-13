package com.ehem.kakaopay.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumericUtilsTest {

    @Test
    void 따옴표와_쉼표로_자릿수가_구분된_숫자문자열_파싱_테스트() {
        String input = "2016,6,\"7,348\",\"6,750\",\"3,207\",\"2,724\",2,\"3,789\",\"1,838\",43,\"4,627\",\"7,348\",\"1,167.54\",\"7\",\"272.4\",2,\"3,789\"";

        String parsedData = NumericUtils.removeCommaSeparatorAndQuotesFrom(input);

        assertThat(parsedData).isEqualTo("2016,6,7348,6750,3207,2724,2,3789,1838,43,4627,7348,1167.54,7,272.4,2,3789");
    }
}