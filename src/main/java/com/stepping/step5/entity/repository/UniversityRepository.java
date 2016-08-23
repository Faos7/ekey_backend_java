package com.stepping.step5.entity.repository;

import com.stepping.step5.entity.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UniversityRepository extends JpaRepository<University, Integer> {

    /*@Query("select u from  University u  where u.name = :name")*/
    //University findByName(@Param("name") String name);

    //public University findByName(String name);
}