package com.ekey.repository;

import com.ekey.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UniversityRepository extends JpaRepository<University, Integer> {
    Optional<University> findOneByName(String name);
}
