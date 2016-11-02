package com.stepping.step5.service;

import com.stepping.step5.models.out.UniversityOut;

import java.util.Collection;

/**
 * Created by re5 on 02.11.16.
 */
public interface UniversityService {

    UniversityOut getUniverdityById(Integer id);

    Collection<UniversityOut> getAllUniversities();
}
