package com.seminar.seminar.service;

import com.seminar.seminar.entity.Course;
import com.seminar.seminar.repository.CourseRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    @Transactional(readOnly = true)
    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }
    @Transactional
    public void save(Course course) {
        courseRepository.save(course);
    }
    @Transactional
    public void delete(Course course) {
        courseRepository.delete(course);
    }
}

