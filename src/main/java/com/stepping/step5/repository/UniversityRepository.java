package com.stepping.step5.repository;

import com.stepping.step5.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UniversityRepository extends JpaRepository<University, Integer> {
    Optional<University> findOneByName(String name);
}
