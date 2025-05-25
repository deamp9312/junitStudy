package com.junitTest.demo.example.ch2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class Basic2_1AssertionsTest {
    /**
     * 조건부 테스트 기본 애너테이션 종류
     * 애너테이션	설명
     * @EnabledOnOs 지정한 운영체제에서만 테스트 실행
     * @DisabledOnOs 지정한 운영체제에서만 테스트 비활성화
     * @EnabledOnJre 지정한 JRE(Java 버전)에서만 실행
     * @DisabledOnJre 지정한 JRE에서만 실행 금지
     * @EnabledIf / @DisabledIf	커스텀 조건 사용
     * (JUnit에는 기본 포함 안됨, 확장 사용 시 사용 가능)
     */
    @Test
    @EnabledOnOs(OS.MAC)
    void onlyOnMac() {
        System.out.println("이 테스트는 macOS 에서만 실행됩니다.");
    }
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void onlyOnWin() {
        System.out.println("이 테스트는 Windows 에서만 실행됩니다.");
    }

    @Test
    @DisabledOnOs(OS.MAC)
    void notOnMac() {
        System.out.println("이 테스트는 MAC 에서 실행되지 않습니다.");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void notOnWindows() {
        System.out.println("이 테스트는 Windows 에서 실행되지 않습니다.");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_21)
    void onlyOnJava21() {
        System.out.println("Java 21에서만 실행됩니다.");
    }
    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void onlyOnJava17() {
        System.out.println("Java 17에서만 실행됩니다.");
    }
    @Test
    @DisabledOnJre(JRE.JAVA_21)
    void notOnJava21() {
        System.out.println("Java 21에서는 실행되지 않습니다.");
    }
    @Test
    @DisabledOnJre(JRE.JAVA_17)
    void notOnJava17() {
        System.out.println("Java 17에서는 실행되지 않습니다.");
    }

}
