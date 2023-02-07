package com.seminar.seminar.service.initializer;

import com.seminar.seminar.entity.Course;
import com.seminar.seminar.entity.Student;
import com.seminar.seminar.entity.Teacher;
import com.seminar.seminar.repository.CourseRepository;
import com.seminar.seminar.repository.StudentRepository;
import com.seminar.seminar.repository.TeacherRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@Order(3)
public class CourseInitializer implements ApplicationRunner {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseInitializer(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Course name = Course.builder()
                .studentName("")
                .teacherName("")
                .build();

        name.setTeachers(Set.of(Objects.requireNonNull(teacherRepository.findById(1L).orElse(null))));
//  courseRepository.saveAll(Set.of(name));

    }
}
