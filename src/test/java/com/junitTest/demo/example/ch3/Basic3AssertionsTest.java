package com.junitTest.demo.example.ch3;

import com.junitTest.demo.example.ch2.Basic2AssertionsTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class Basic3AssertionsTest {
    private static final Logger log = LoggerFactory.getLogger(Basic3AssertionsTest.class);
    @Test
    void shouldThrowExceptionWhenDivideByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });

        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void testArrayEquals() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};

        assertArrayEquals(expected, actual);
    }

    /**
     * assertIterableEquals
     * List, Set 등의 순서 있는 컬렉션 비교
     */
    @Test
    void testIterableEquals() {
        List<String> expected = List.of("apple", "banana");
        List<String> actual = List.of("apple", "banana");

        assertIterableEquals(expected, actual);
    }

    /**
     * assertLinesMatch
     * 줄 단위 문자열 비교 (정규식도 지원)
     */
    @Test
    void testLinesMatch() {
        List<String> expected = List.of("Hello", "\\d+ lines");
        List<String> actual = List.of("Hello", "3 lines");

        assertLinesMatch(expected, actual);
    }

    /**
     *assertAll
     * 복수의 assert를 한꺼번에 실행. 중간에 실패해도 모두 실행.
     */
    @Test
    void testMultipleAssertions() {
        String str = "JUnit";

        assertAll("문자열 테스트",
                () -> assertEquals(5, str.length()),
                () -> assertTrue(str.startsWith("J")),
                () -> assertFalse(str.contains("z"))
        );
    }
    /**
     * 조건부 Assumptions
     * Assumptions는 특정 조건이 만족되지 않으면 테스트를 건너뛰게 할 때 사용합니다.
     * (실패 처리 ❌ → Skip 처리 ✅)
     */
    @Test
    void testWithAssumeTrue() {
        assumeTrue(System.getProperty("os.name").contains("Mac"));

        // Mac일 경우에만 실행
        log.info("이 코드는 Mac OS에서만 실행됩니다.");
    }

    /**
     * assertThrows	예외 발생 여부 확인	assertThrows(IllegalArgumentException.class, () -> method())
     * assertAll	여러 assert를 한 번에 검사	assertAll(...asserts)
     * assertArrayEquals	배열 비교	assertArrayEquals(expected, actual)
     * assertIterableEquals	컬렉션 비교	assertIterableEquals(expected, actual)
     * assertLinesMatch	줄 단위 정규식 문자열 비교	assertLinesMatch(expected, actual)
     * assumeTrue, assumeFalse	조건 만족하지 않으면 테스트 스킵	assumeTrue(condition)
     */




}
