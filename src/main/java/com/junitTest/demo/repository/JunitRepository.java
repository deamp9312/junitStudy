package com.junitTest.demo.repository;

import com.junitTest.demo.entity.JunitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JunitRepository extends JpaRepository<JunitEntity, Long> {
}
