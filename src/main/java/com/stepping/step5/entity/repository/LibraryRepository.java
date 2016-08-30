package com.stepping.step5.entity.repository;

import com.stepping.step5.entity.models.Library;
import com.stepping.step5.entity.models.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LibraryRepository extends CrudRepository<Library, Integer> {

    List<Library> findByUniversity(University university);

}
