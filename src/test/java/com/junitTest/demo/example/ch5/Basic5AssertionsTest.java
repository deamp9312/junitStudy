//package com.junitTest.demo.example.ch5;
//
//import com.junitTest.demo.entity.JunitEntity;
//import com.junitTest.demo.repository.JunitRepository;
//import com.junitTest.demo.service.JunitService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class Basic5AssertionsTest {
//    /**
//     * Mockito란?
//     * 자바 단위 테스트에서 의존 객체(Dependency)를 가짜(Mock)로 만들어 테스트하는 프레임워크
//     * <p>
//     * 진짜 DB나 네트워크 없이 테스트 가능 = 빠르고 독립적인 테스트
//     * <p>
//     * 🔹 용어 정리
//     * 개념	설명
//     * Mock	가짜 객체. 미리 정의된 행동만 수행
//     * Stub	Mock의 하위 개념. 특정 입력에 대한 특정 결과를 반환하도록 설정
//     * Spy	실제 객체를 감싼 객체. 일부는 실제 메서드 실행, 일부는 mocking 가능
//     */
//
//    @Mock
//    JunitRepository repository;
//
//    @InjectMocks
//    JunitService service;
//
//    @Test
//    void testFind() {
//        when(repository.findById(1L)).thenReturn(Optional.of(new JunitEntity(1L, "name")));
//
//        JunitEntity result = service.getData(1L);
//
//        assertEquals("name", result.getName());
//    }
//    /**
//     * when(...).thenReturn(...)	지정된 입력에 대해 결과 반환
//     * when(...).thenThrow(...)	예외 발생시킴
//     * doReturn(...).when(mock).method(...)	void 메서드 또는 spy 객체용
//     * doThrow(...).when(mock).method(...)	void 메서드 예외용
//     */
//
//    @Test
//    void testVerify() {
//        service.getData(1L);
//        verify(repository).findById(1L); // 1번 호출되었는지 확인
//    }
//
//    @Captor
//    ArgumentCaptor<Long> captor;
//
//    @Test
//    void testCaptor() {
//        when(repository.findById(5L)).thenReturn(Optional.of(new JunitEntity(5L, "name")));
//
//        service.getData(5L);
//        Long capture = captor.capture();
//        verify(repository).findById(capture);
//        assertEquals(5L, captor.getValue());
//    }
//
//    /**
//     * spy – 실제 객체 감싸기
//     *
//     * Mock은 기본적으로 아무것도 하지 않음 → 명시적으로 when() 으로 행동 정의해야 함
//     *
//     * Spy는 실제 객체 기반이므로 조심히 사용
//     *
//     * verify()는 동작 결과 뿐 아니라 “동작 방식”을 테스트할 때 중요
//     */
//    @Test
//    void spyTest(){
//        List<String> list = new ArrayList<>();
//        List<String> spyList = spy(list);
//
//        doReturn("mocked").when(spyList).get(0);
//        spyList.add("real");
//
//        assertEquals("real", spyList.get(0)); // spy이므로 진짜 객체에 접근 가능
//
//    }
//
//
//    /**
//     * : 단순 리턴값 지정 (when().thenReturn())
//     */
//    @Test
//    void testGetNameReturnsMockedValue() {
//
//        when(repository.findById(1L)).thenReturn("MockedName");
//
//        String name = service.getData(1L).getName();
//
//        assertEquals("MockedName", name);
//    }
//
//
//
//    @Test
//    @DisplayName("Mock 객체로 리턴값 지정: thenReturn")
//    void testMock_thenReturn() {
//        JunitEntity mockEntity = new JunitEntity();
//        mockEntity.setId(1L);
//        mockEntity.setName("MockedName");
//
//        // findById 호출 시 Optional로 감싸서 리턴
//        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
//
//        JunitEntity result = service.getData(1L);
//
//        assertEquals("MockedName", result.getName());
//    }
//    @Test
//    @DisplayName("Mock 객체로 예외 던지기: doThrow")
//    void testMock_doThrow() {
//        doThrow(new IllegalArgumentException("잘못된 인자"))
//                .when(repository).deleteById(-1L);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            repository.deleteById(-1L);
//        });
//    }
//
//    @Test
//    @DisplayName("Mock 메서드 호출 검증: verify")
//    void testVerifyCalled() {
//        Long id = 1L;
//        when(repository.findById(id)).thenReturn(Optional.of(new JunitEntity()));
//
//        service.getData(id);
//
//        // findById가 1번 호출되었는지 확인
//        verify(repository, times(1)).findById(id);
//    }
//
//    @Captor
//    ArgumentCaptor<Long> idCaptor;
//
//    @Test
//    @DisplayName("ArgumentCaptor 사용 예제")
//    void testArgumentCaptor() {
//        JunitEntity entity = new JunitEntity();
//        entity.setId(1L);
//        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
//
//        service.getData(1L);
//
//        verify(repository).findById(idCaptor.capture());
//        assertEquals(1L, idCaptor.getValue());
//    }
//
//    @Test
//    @DisplayName("Spy 객체 사용 예제")
//    void testSpy() {
//        List<String> spyList = spy(new ArrayList<>());
//
//        spyList.add("one");
//        spyList.add("two");
//
//        verify(spyList).add("one");
//        verify(spyList).add("two");
//
//        assertEquals(2, spyList.size());
//    }
//    /**
//     * | 기능                       | 목적            | 예시 메서드                   |
//     * | ------------------------ | ------------- | ------------------------ |
//     * | `@Mock`                  | 가짜 객체 생성      | `@Mock JunitRepository`  |
//     * | `when(...).thenReturn()` | 특정 값 반환 설정    | `when(repo.findById())`  |
//     * | `doThrow()`              | 예외 던지기        | `doThrow(...).when(...)` |
//     * | `verify()`               | 호출 여부 검증      | `verify(...).method()`   |
//     * | `ArgumentCaptor`         | 전달된 인자 값 검증   | `ArgumentCaptor<Long>`   |
//     * | `@Spy` / `spy()`         | 진짜 객체 + 부분 스텁 | `spy(new ArrayList<>())` |
//     */
//
//
//
//
//}
