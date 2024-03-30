package com.ktown4u.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class NeutralizerTest {
    @DisplayName("문자열에 포한된 LocalDateTime의 문자열 표현을 특정 문자로 변환하여 테스트에서 매번 변경되는 LocalDateTime을 대응한다")
    @Test
    void neutralizeLocalDateTime() {
        // Given
        String string = "GoodsFamily{id=1, name='name', createdBy=1, updatedBy=null, createdAt=2021-08-01T00:00:00.000000, updatedAt=null, goodsFamily2Goods=[]}";

        // When
        String replacement = "2003-05-03T10:11:12.134567";
        String regex = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{6}";
        Pattern pattern = Pattern.compile(regex);
        String result = pattern.matcher(string).replaceAll(replacement);

        // Then
        assertThat(result).contains("2003-05-03T10:11:12.134567");
    }
}