package com.stepping.step5.entity.repository;

import com.stepping.step5.entity.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
