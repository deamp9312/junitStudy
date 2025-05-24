package com.junitTest.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class JunitEntity {

    @Id
    private Long id;

    private String name;
    private String phonNm;
    private String email;
}
