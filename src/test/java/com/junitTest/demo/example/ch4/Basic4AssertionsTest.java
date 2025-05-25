package com.junitTest.demo.example.ch4;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Basic4AssertionsTest {

    /**
     * @ValueSource 단일 파라미터 (int, String 등)
     * @CsvSource 여러 인자를 CSV로 전달
     * @MethodSource 메서드에서 동적 데이터 제공
     * @EnumSource enum 타입 테스트용
     * ArgumentConverter	단일 인자 변환 로직 작성 가능
     * ArgumentsAggregator	다중 인자 → 객체로 집계해 전달 가능
     */
    private static final Logger log = LoggerFactory.getLogger(Basic4AssertionsTest.class);

//    @BeforeEach
//    public void beforeEach(){
//        log.info("beforeEach()실행됨");
//    }
//    @AfterEach
//    public void afterEach(){
//        log.info("afterEach()실행됨");
//    }
    /**
     * Parameterized Tests는 하나의 테스트 메서드를 다양한 인자 값으로 반복 실행할 수 있게 해주는 기능입니다.
     * 이 기능은 테스트 중복을 줄이고 다양한 입력에 대해 동일한 로직을 테스트할 수 있도록 도와줍니다.
     */
    @ParameterizedTest
    @ValueSource(strings = {"apple", "banana", "orange"})
    void testStringLength(String input) {
        //before aftereach 각각의 입력마다 N번 수행됨 주의!!
        log.info("input : [{}]",input);
        assertTrue(input.length() > 0);
    }

    /**
     * @ValueSource: 기본형/문자열 배열 제공
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void testEvenNumber(int num) {
        assertTrue(num > 0);
    }

    /**
     * @CsvSource: CSV 형태로 여러 인자 전달
     */
    @ParameterizedTest
    @CsvSource({
            "apple, 5",
            "banana, 6",
            "cherry, 6"
    })
    void testStringLength(String word, int expectedLength) {
        assertEquals(expectedLength, word.length());
    }


    /**
     * @MethodSource: 메서드에서 직접 데이터 제공
     */
    static Stream<String> provideStrings() {
        return Stream.of("dog", "cat", "fish");
    }

    @ParameterizedTest
    @MethodSource("provideStrings")
    void testLengthNotZero(String input) {
        assertFalse(input.isEmpty());
    }

    /**
     * @EnumSource: enum 값을 테스트 인자로 사용
     */
    enum Animal { DOG, CAT, FISH }

    @ParameterizedTest
    @EnumSource(Animal.class)
    void testEnumNotNull(Animal animal) {
        assertNotNull(animal);
    }

    /**
     * 고급: 커스텀 인자 변환기와 집계자
     * 📌 ArgumentConverter: 하나의 값 변환
     * 실행이 안됨 주의!
     */
//    class StringToUpperCaseConverter extends SimpleArgumentConverter {
//        @Override
//        protected Object convert(Object source, Class<?> targetType) {
//            if (!targetType.equals(String.class)) {
//                throw new IllegalArgumentException("Only String is supported");
//            }
//            return ((String) source).toUpperCase();
//        }
//    }
//
//        @ParameterizedTest
//        @ValueSource(strings = {"test", "junit"})
//        void testConvertToUpper(@ConvertWith(StringToUpperCaseConverter.class) String value) {
//            assertEquals(value, value.toUpperCase());
//        }

    /**
     * ArgumentsAggregator: 여러 값 집계
     */
    class Person {
        String name;
        int age;
        Person(String name, int age) { this.name = name; this.age = age; }
    }

    class PersonAggregator implements ArgumentsAggregator {
        @Override
        public Person aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) {
            return new Person(accessor.getString(0), accessor.getInteger(1));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "Alice, 30",
            "Bob, 25"
    })
    void testPersonAggregator(@AggregateWith(PersonAggregator.class) Person person) {
        assertTrue(person.age > 20);
    }



}
