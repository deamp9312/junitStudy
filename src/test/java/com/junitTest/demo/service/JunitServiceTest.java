package com.junitTest.demo.service;

import com.junitTest.demo.entity.JunitEntity;
import com.junitTest.demo.repository.JunitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JunitServiceTest {

    @Mock
    JunitRepository repository;

    @InjectMocks
    JunitService service;

    @Test
    public void getDataTest() {
        // given (mock data 준비)
        Long inputId = 13L;
        JunitEntity mockEntity = new JunitEntity();
        mockEntity.setId(inputId);
        mockEntity.setName("테스트");

        when(repository.findById(inputId)).thenReturn(Optional.of(mockEntity));

        // when (서비스 호출)
        JunitEntity data = service.getData(inputId);

        // then (검증)
        assertNotNull(data);
        assertEquals(inputId, data.getId());
        assertEquals("테스트2", data.getName(),"mock 이름가져오기 테스트실패");

        // verify (repository가 올바르게 호출되었는지도 확인 가능)
        verify(repository).findById(inputId);
    }
}
