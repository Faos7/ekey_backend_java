package com.ekey.repository;

import com.ekey.models.Library;
import com.ekey.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface LibraryRepository extends JpaRepository<Library, Integer> {

    List<Library> findByUniversity(University university);

    Optional<Library> findOneByName(String name);

}
