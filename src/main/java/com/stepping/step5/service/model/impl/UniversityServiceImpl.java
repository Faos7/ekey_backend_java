package com.stepping.step5.service.model.impl;


import com.stepping.step5.service.model.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {/*

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityServiceImpl.class);

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<University> getUniversityById(int id) {
        LOGGER.debug("Getting university={}", id);
        return Optional.ofNullable(universityRepository.findOne(id));
    }

    @Override
    public Collection<Library> getAllUniversityLibraries(String name) {
        LOGGER.debug("Getting university libraries, university name={}", name);
        Optional<University> university = universityRepository.findOneByName(name);
        Collection<Library> libraries = university.get().getLibraries();
        return libraries;
    }

    @Override
    public Collection<Faculty> getAllUniversityFaculties(String name) {
        LOGGER.debug("Getting university faculties, university name={}", name);
        Optional<University> university = universityRepository.findOneByName(name);
        Collection<Faculty> faculties = university.get().getFaculties();
        return faculties;
    }

    @Override
    public Optional<University> getUniversityByName(String name) {
        LOGGER.debug("Getting university by name={}", name);
        return universityRepository.findOneByName(name);
    }

    @Override
    public Collection<University> getAllUniversities() {
        LOGGER.debug("Getting all universities");
        return universityRepository.findAll(new Sort("name"));
    }

    @Override
    public University create(UniversityCreateForm form) {
        University university = new University();
        university.setName(form.getName());
        return universityRepository.save(university);
    }*/
}
