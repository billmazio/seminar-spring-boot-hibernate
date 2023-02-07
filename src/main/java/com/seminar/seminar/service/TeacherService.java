
package com.seminar.seminar.service;


import com.seminar.seminar.entity.Teacher;
import com.seminar.seminar.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
       return teacherRepository.findAll();
    }

}

