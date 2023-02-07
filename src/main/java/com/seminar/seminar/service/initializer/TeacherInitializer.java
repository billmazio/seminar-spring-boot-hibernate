package com.seminar.seminar.service.initializer;

import com.seminar.seminar.entity.Teacher;
import com.seminar.seminar.repository.TeacherRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Order(1)
public class TeacherInitializer implements ApplicationRunner {
    private final TeacherRepository teacherRepository;

    public TeacherInitializer(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Teacher specialty = Teacher.builder()
                .specialty("")

                .build();

        teacherRepository.saveAll(Set.of());
    }
}
