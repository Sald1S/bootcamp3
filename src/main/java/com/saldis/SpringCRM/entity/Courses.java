package com.saldis.SpringCRM.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "courseName")
    private String  courseName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;
}
