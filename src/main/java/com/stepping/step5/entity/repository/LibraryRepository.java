package com.stepping.step5.entity.repository;

import com.stepping.step5.entity.models.Library;
import com.stepping.step5.entity.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    List<Library> findByUniversity(University university);

}
