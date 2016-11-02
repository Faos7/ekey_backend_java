package com.stepping.step5.service.impl;

import com.stepping.step5.models.Library;
import com.stepping.step5.models.University;
import com.stepping.step5.models.out.LibraryOut;
import com.stepping.step5.repository.LibraryRepository;
import com.stepping.step5.repository.UniversityRepository;
import com.stepping.step5.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by re5 on 02.11.16.
 */
@Service
public class LibraryServiceImpl implements LibraryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryServiceImpl.class);

    private LibraryRepository libraryRepository;
    private UniversityRepository universityRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, UniversityRepository universityRepository) {
        this.libraryRepository = libraryRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public LibraryOut getLibraryById(Integer id) {
        LOGGER.debug("Getting library={}", id);

        return new LibraryOut(libraryRepository.findOne(id));
    }

    @Override
    public Collection<LibraryOut> getAllLibraries() {
        LOGGER.debug("Getting all libraries");
        Collection<Library> libraries = libraryRepository.findAll(new Sort("name"));
        Collection<LibraryOut> result = new ArrayList<>();
        for (Library library: libraries){
            result.add(new LibraryOut(library));
        }
        return result;
    }

    @Override
    public Collection<LibraryOut> getAllUniversityLibraries(Integer id) {
        LOGGER.debug("Getting all libraries with university={}", id);
        University university = universityRepository.findOne(id);
        Collection<Library> libraries = university.getLibraries();
        Collection<LibraryOut> result = new ArrayList<>();
        for (Library library: libraries){
            result.add(new LibraryOut(library));
        }
        return result;
    }
}
