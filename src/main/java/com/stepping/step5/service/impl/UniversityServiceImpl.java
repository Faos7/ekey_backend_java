package com.stepping.step5.service.impl;

import com.stepping.step5.models.University;
import com.stepping.step5.models.out.UniversityOut;
import com.stepping.step5.repository.UniversityRepository;
import com.stepping.step5.service.UniversityService;
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
