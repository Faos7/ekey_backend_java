package com.ekey.service.impl;

import com.ekey.models.Faculty;
import com.ekey.models.University;
import com.ekey.models.out.FacultyOut;
import com.ekey.repository.FacultyRepository;
import com.ekey.repository.UniversityRepository;
import com.ekey.service.FacultyService;
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
public class FacultyServiceImpl implements FacultyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public FacultyOut getFacultyById(Integer id) {
        LOGGER.debug("Getting faculty={}", id);

        return new FacultyOut(facultyRepository.findOne(id));
    }

    @Override
    public Collection<FacultyOut> getAllFaculties() {
        LOGGER.debug("Getting all faculties");
        Collection<Faculty> faculties = facultyRepository.findAll(new Sort("name"));
        Collection<FacultyOut> result = new ArrayList<>();
        for (Faculty faculty: faculties){
            result.add(new FacultyOut(faculty));
        }
        return result;
    }

    @Override
    public Collection<FacultyOut> getAllUniversityFaculties(Integer id) {
        LOGGER.debug("Getting all faculties with university={}", id);
        University university = universityRepository.findOne(id);
        Collection<Faculty> faculties = university.getFaculties();
        Collection<FacultyOut> result = new ArrayList<>();
        for (Faculty faculty: faculties){
            result.add(new FacultyOut(faculty));
        }
        return result;
    }
}
