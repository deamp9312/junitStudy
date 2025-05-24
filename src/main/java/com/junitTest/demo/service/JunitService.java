package com.junitTest.demo.service;

import com.junitTest.demo.entity.JunitEntity;
import com.junitTest.demo.repository.JunitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JunitService {

    @Autowired
    private JunitRepository repository;
    public JunitEntity getData(Long id){
        Optional<JunitEntity> byId = repository.findById(id);
        return byId.get();

    }
}
