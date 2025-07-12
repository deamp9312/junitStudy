package com.junitTest.demo.controller;

import com.junitTest.demo.entity.JunitEntity;
import com.junitTest.demo.repository.JunitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class JunitController {

    @Autowired
    JunitRepository repository;

    @PostMapping("/save")
    public String testA1(@RequestBody JunitEntity entity) {
        repository.save(entity);
        return "test";
    }

    @GetMapping("/{id}")
    public JunitEntity getId(@PathVariable Long id) {
        return repository.findById(id).get();
    }
}
