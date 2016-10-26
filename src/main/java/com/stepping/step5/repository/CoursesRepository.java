package com.stepping.step5.repository;

import com.stepping.step5.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CoursesRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findOneByNumber(int number);
}
