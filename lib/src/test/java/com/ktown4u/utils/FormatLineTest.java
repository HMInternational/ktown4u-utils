package com.ktown4u.utils;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FormatLineTest {
    @DisplayName("name, value, columns를 입력하면 name과 value 사이에 columns만큼의 공백을 추가한 문자열을 반환한다")
    @Test
    void case0() {
        // given
        final String name = "name";
        final Object value = "value";
        final int columns = 10;

        // when
        final String result = formatLineWithWhitespaces(name, value, columns);

        // then
        Approvals.verify(result);
    }
}