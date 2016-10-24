package com.stepping.step5.repository;

import com.stepping.step5.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CoursesRepository extends JpaRepository<Course, Integer> {
}
