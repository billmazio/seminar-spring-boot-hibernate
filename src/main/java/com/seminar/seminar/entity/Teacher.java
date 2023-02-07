package com.seminar.seminar.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "TEACHER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;
    private String specialty;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,}, mappedBy = "teachers")
    private Set<Course> courses;


}
