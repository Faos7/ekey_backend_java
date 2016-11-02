package com.stepping.step5.service;

import com.stepping.step5.models.out.FacultyOut;

import java.util.Collection;

/**
 * Created by re5 on 02.11.16.
 */
public interface FacultyService {
    FacultyOut getFacultyById(Integer id);

    Collection<FacultyOut> getAllFaculties();

    Collection<FacultyOut> getAllUniversityFaculties(Integer id);

}
