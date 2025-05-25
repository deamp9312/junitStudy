package com.junitTest.demo.example.ch2;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class Basic2AssertionsTest {

    private static final Logger log = LoggerFactory.getLogger(Basic2AssertionsTest.class);


    private List<Object> list;


    /**
     *@BeforeAll
     * 클래스 전체에서 단 한 번 실행되는 초기화 메서드.
     *
     * 보통 DB 연결, 리소스 준비, 무거운 초기 작업에 사용.
     *
     * 반드시 static 메서드로 선언해야 함.
     */
    @BeforeAll
    static void globalSetUp() {
        log.info("== 테스트 전체 시작 전 한 번 실행 ==");
    }
    /**
     * @AfterAll
     * 클래스 전체 테스트가 끝난 후 단 한 번 실행됨.
     *
     * 리소스 정리, 연결 종료 등에 사용.
     *
     * static 메서드로 선언.
     */
    @AfterAll
    static void globalTearDown() {
        log.info("== 테스트 전체 종료 후 한 번 실행 ==");
    }
    /**
     * @BeforeEach
     * 각 테스트 메서드 실행 전에 매번 실행됨.
     *
     * 테스트 실행을 위한 초기화 코드를 작성할 때 사용.
     */
    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        list.add("초기값");
        log.info("setUp() 수행");
    }
    /**
     * @AfterEach
     * 각 테스트 메서드 실행 후 매번 실행됨.
     *
     * 테스트 후 리소스 해제, 정리 작업에 주로 사용.
     */
    @AfterEach
    void tearDown() {
        list.clear(); // 테스트 후 정리
        log.info("tearDown() 수행");
    }

    /**
     * @DisplayName
     * 테스트 클래스나 테스트 메서드에 설명을 달아 테스트 결과 출력 시 가독성 향상.
     */
    @DisplayName("리스트 초기화 테스트")
    @Test
    void testListInit() {
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("리스트에 값 추가 후 확인")
    void testAddElement() {
        list.add("Test");
        assertTrue(list.contains("Test"), "리스트에 'Test' 포함되어야 함");
    }


    @Nested
    @DisplayName("추가 테스트")
    class AddTests {

        @Test
        @DisplayName("리스트에 추가한 요소가 포함되는지 테스트")
        void testAdd() {
            list.add("Hello");
            assertTrue(list.contains("Hello"));
        }

    }
    @Nested
    @DisplayName("삭제 테스트")
    class RemoveTests {

        @Test
        @DisplayName("리스트에서 요소를 제거하는지 테스트")
        void testRemove() {
            list.remove("JUnit");
            assertFalse(list.contains("JUnit"));
        }
    }
}
