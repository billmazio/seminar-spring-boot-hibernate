package com.seminar.seminar.service.initializer;

import com.seminar.seminar.entity.Student;
import com.seminar.seminar.repository.StudentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Order(2)
public class StudentInitializer implements ApplicationRunner {
    private final StudentRepository studentRepository;

    public StudentInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Student email = Student.builder()
                .email("")

                .build();

        studentRepository.saveAll(Set.of());

    }
}
