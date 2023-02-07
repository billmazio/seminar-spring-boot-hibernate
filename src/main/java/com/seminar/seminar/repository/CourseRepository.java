package com.seminar.seminar.repository;

import com.seminar.seminar.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
   @Query( value = "SELECT * FROM COURSE C  WHERE name  like :name% ", nativeQuery = true )
    //@Query(value = "SELECT * FROM COURSE  WHERE NAME LIKE BINARY CONCAT(:name,'%')", nativeQuery = true)
    List<Course> findByName(@Param("name")String name);



}
