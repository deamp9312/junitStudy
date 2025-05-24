package com.junitTest.demo.service;

import com.junitTest.demo.entity.JunitEntity;
import com.junitTest.demo.repository.JunitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Junit2ServiceTest {
    private JunitRepository repository;
    private Junit2Service service;

    @BeforeEach
    void setUp() {
        repository = mock(JunitRepository.class);
        service = new Junit2Service(repository); // 생성자 주입으로 간단!
    }

    @Test
    void getDataTest() {
        Long id = 1L;
        JunitEntity mockEntity = new JunitEntity();
        mockEntity.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(mockEntity));

        JunitEntity result = service.getData(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

}