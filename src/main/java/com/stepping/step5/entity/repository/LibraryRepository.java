package com.stepping.step5.entity.repository;

import com.stepping.step5.entity.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LibraryRepository extends JpaRepository<Library, Integer> {
}
