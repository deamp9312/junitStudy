package com.junitTest.demo.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionExamplesTest {

    @Test
    void testAssertEquals() {
        int expected = 10;
        int actual = 5 + 5;
        assertEquals(expected, actual, "정수 값이 일치하지 않음");
    }
    @Test
    void testAssertNotEquals() {
        String result = "fail";
        assertNotEquals("success", result, "결과가 success가 아니어야 함");
    }

    @Test
    void testAssertTrue() {
        boolean condition = 10 > 5;
        assertTrue(condition, "조건이 true여야 함");
    }

    @Test
    void testAssertFalse() {
        boolean condition = 3 > 10;
        assertFalse(condition, "조건이 false여야 함");
    }
    @Test
    void testAssertNull() {
        Object obj = null;
        assertNull(obj, "객체가 null이어야 함");
    }
    @Test
    void testAssertNotNull() {
        String str = "hello";
        assertNotNull(str, "객체가 null이 아니어야 함");
    }
    @Test
    void testAssertSame() {
        String str = "test";
        String sameStr = str;
        assertSame(str, sameStr, "같은 객체여야 함");
    }
    @Test
    void testAssertNotSame() {
        String str1 = new String("test");
        String str2 = new String("test");
        assertNotSame(str1, str2, "서로 다른 객체여야 함");
    }
    @Test
    void testAssertArrayEquals() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual, "배열이 일치하지 않음");
    }

    @Test
    void testAssertIterableEquals() {
        List<String> expected = List.of("a", "b");
        List<String> actual = List.of("a", "b");
        assertIterableEquals(expected, actual, "리스트가 일치하지 않음");
    }

    @Test
    void testAssertThrows() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("잘못된 입력");
        });
        assertEquals("잘못된 입력", ex.getMessage(), "예외 메시지가 일치해야 함");
    }

    @Test
    void testAssertAll() {
        String str = "hello";

        assertAll("문자열 검증",
                () -> assertNotNull(str, "null이 아니어야 함"),
                () -> assertEquals(5, str.length(), "문자열 길이가 5여야 함"),
                () -> assertTrue(str.startsWith("he"), "he로 시작해야 함")
        );
    }











}
