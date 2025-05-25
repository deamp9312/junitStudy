package com.junitTest.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class JunitEntity {

    @Id
    private Long id;

    private String name;
    private String phonNm;
    private String email;

    public JunitEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
