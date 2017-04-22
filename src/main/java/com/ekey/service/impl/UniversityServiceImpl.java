package com.ekey.service.impl;

import com.ekey.models.University;
import com.ekey.service.UniversityService;
import com.ekey.models.out.UniversityOut;
import com.ekey.repository.UniversityRepository;
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
public class UniversityServiceImpl implements UniversityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversityServiceImpl.class);

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public UniversityOut getUniverdityById(Integer id) {
        LOGGER.debug("Getting university={}", id);

        return new UniversityOut(universityRepository.findOne(id));
    }

    @Override
    public Collection<UniversityOut> getAllUniversities() {
        LOGGER.debug("Getting all universities");
        Collection<University> universities = universityRepository.findAll(new Sort("name"));
        Collection<UniversityOut> result = new ArrayList<>();
        for (University university: universities){
            result.add(new UniversityOut(university));
        }
        return result;
    }
}
