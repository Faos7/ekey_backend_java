package com.stepping.step5.repository;

import com.stepping.step5.models.Library;
import com.stepping.step5.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    List<Library> findByUniversity(University university);

}
