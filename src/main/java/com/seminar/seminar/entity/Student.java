package com.seminar.seminar.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String email;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,}, mappedBy = "students")
    private Set<Course> courses;

}
