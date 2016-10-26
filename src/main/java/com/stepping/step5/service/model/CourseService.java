package com.stepping.step5.service.model;


import com.stepping.step5.models.CourseOut;

import java.util.Collection;
import java.util.Optional;

public interface CourseService {

    CourseOut getCourseById(int id);

    CourseOut getCourseByNumber(int number);

    Collection<CourseOut> getAllCourses();
/*
    Course create(CourseCreateForm form);

    Collection<Group> getAllCourseGroups(int number);
    */
}
