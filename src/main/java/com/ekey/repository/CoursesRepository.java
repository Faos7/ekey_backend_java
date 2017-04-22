package com.ekey.repository;

import com.ekey.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CoursesRepository extends JpaRepository<Course, Integer> {
}
