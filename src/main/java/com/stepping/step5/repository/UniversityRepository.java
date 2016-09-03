package com.stepping.step5.repository;

import com.stepping.step5.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
