package com.seminar.seminar.controller;

import com.seminar.seminar.entity.Course;
import com.seminar.seminar.entity.Student;
import com.seminar.seminar.entity.Teacher;
import com.seminar.seminar.service.CourseService;
import com.seminar.seminar.service.StudentService;
import com.seminar.seminar.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@Slf4j
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }
    @GetMapping("/courses")
    public String getAllCourses(Model model, @Param("name")String name) {
        List<Course> courses = new ArrayList<Course>();
        if(name!=null) {
            courses.addAll(courseService.findByName(name));
        }else {
            courseService.findAll().forEach(courses::add);
            model.addAttribute("name",name);
        }
        model.addAttribute("courses",courses);
        model.addAttribute("courseList",courseService.findAll());
        return "courses";
    }
    @GetMapping("/updateCourse/{courseId}")
    public String updateCourse(@PathVariable("courseId")Long id,Model model) {
        Optional<Course> courseOptional = courseService.findById(id);
        courseOptional.ifPresent(course -> model.addAttribute("course",course));
        model.addAttribute("allTeachers",teacherService.findAll());
        model.addAttribute("allStudents",studentService.findAll());
        return "courseFormEdit";
    }
   @PostMapping("/edit")
   public String registrationForm(@Valid @ModelAttribute("course") Course course ,
                                  BindingResult result) {
       if (result.hasErrors()) {
           log.debug("Page has errors");
           return "courseFormEdit";
       }
       Optional<Course> courseOptional = courseService.findById(course.getId());
       courseOptional.ifPresent(courseFormRepo -> {

           Set<Student> currentlyEdit = courseFormRepo.getStudents();
           currentlyEdit.addAll(course.getStudents());
           courseFormRepo.setStudents(currentlyEdit);
           Set<Teacher> currentlyPut = courseFormRepo.getTeachers();
           currentlyPut.addAll(course.getTeachers());
           courseFormRepo.setTeachers(currentlyPut);
           courseService.save(courseFormRepo);
       });
       return "redirect:/courses";
   }


    @PostMapping("/save/update/{id}")
    public String updateCourse(@PathVariable("id") Long id) {
       Optional<Course> courseOptional = courseService.findById(id);
       courseOptional.ifPresent(courseService::save);
       return "redirect:/courses";
    }


    @GetMapping("/add/course")
    public String addCourse(Course course, Model model) {
    course = Course.builder().
                name("").description("").build();
        model.addAttribute("course",course);
        model.addAttribute("teachers",teacherService.findAll());
        model.addAttribute("students", studentService.findAll());

        return "courseForm";
    }
    @PostMapping("/course/save")
    public String saveCourse(Course course) {
      courseService.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/course/delete/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        Optional<Course> courseOptional = courseService.findById(id);
        courseOptional.ifPresent(courseService::delete);
        return "redirect:/courses";
    }
}
