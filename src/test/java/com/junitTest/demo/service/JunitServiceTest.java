package com.junitTest.demo.service;

import com.junitTest.demo.entity.JunitEntity;
import com.junitTest.demo.repository.JunitRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("JunitService 단위 테스트") // 클래스 단위 설명
class JunitServiceTest {

    @Mock
    JunitRepository repository;

    @InjectMocks
    JunitService service;

    private Long inputId;
    private JunitEntity mockEntity;

    @BeforeEach
    void initData() {
        inputId = 13L;
        mockEntity = new JunitEntity();
        mockEntity.setId(inputId);
        mockEntity.setName("테스트");

        when(repository.findById(inputId)).thenReturn(Optional.of(mockEntity));
    }

    @Test
    @DisplayName("데이터 조회 성공 테스트")
    void getDataSuccessTest() {
        // when
        JunitEntity data = service.getData(inputId);

        // then
        assertNotNull(data, "결과가 null이면 안 됩니다.");
        assertEquals(inputId, data.getId(), "ID 값이 일치하지 않습니다.");
        assertEquals("테스트", data.getName(), "이름 값이 예상과 다릅니다.");

        verify(repository).findById(inputId);
    }

    @Test
    @DisplayName("데이터 조회 실패 예외 테스트")
    void getDataFailTest() {
        // given
        Long invalidId = 999L;
        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class, () -> {
            service.getData(invalidId);
        }, "Optional.get() 호출 시 NoSuchElementException이 발생해야 합니다.");

        verify(repository).findById(invalidId);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 이름1",
            "2, 이름2",
            "3, 이름3"
    })
    @DisplayName("여러 ID 값에 대해 데이터 이름 검증")
    void parameterizedGetDataTest(Long id, String expectedName) {
        // given
        JunitEntity entity = new JunitEntity();
        entity.setId(id);
        entity.setName(expectedName);
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        // when
        JunitEntity result = service.getData(id);

        // then
        assertEquals(expectedName, result.getName(), "이름이 일치하지 않습니다.");
    }
}
