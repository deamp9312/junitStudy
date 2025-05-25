package com.junitTest.demo.example.ch1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasicAssertionsTest {

    @Test
    void testAssertEquals() {
        int expected = 5;
        int actual = 2 + 3;
        assertEquals(expected, actual, "더하기 결과가 일치하지 않습니다.");
    }

    @Test
    void testAssertTrue() {
        boolean isPositive = 10 > 0;
        assertTrue(isPositive, "양수 조건이 거짓입니다.");
    }

    @Test
    void testAssertFalse() {
        boolean isNegative = 10 < 0;
        assertFalse(isNegative, "음수 조건이 거짓입니다.");
    }

    @Test
    void testAssertNotNull() {
        String name = "JUnit";
        assertNotNull(name, "null 이 아니어야 합니다.");
    }

    @Test
    void testAssertThrows() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        }, "0으로 나눌 때 예외가 발생해야 합니다.");
    }

//assertEquals(expected, actual) 순서 헷갈리지 마세요! 기대값 → 실제값입니다.

    @Test
    void testStringLength() {
        String text = "hello";
        // 문자열 길이가 5인지 확인해보세요
        assertEquals(text.length(),5,"문장길이는 5입니다.");
    }
    @Test
    void testListIsEmpty() {
        List<String> list = new ArrayList<>();
        // 리스트가 비어있는지 assertTrue/assertEquals 중 하나로 확인
        assertNotNull(list);
        assertEquals(true, list.isEmpty(),"빈값 예상");
    }

    @Test
    void testNullPointerException() {
        String str = null;
        // str.length() 실행 시 NullPointerException 발생하는지 확인
        assertThrows(NullPointerException.class, () ->
                str.length());
    }





}
