package com.stepping.step5.service.model.impl;

import com.stepping.step5.models.CourseOut;
import com.stepping.step5.repository.CoursesRepository;
import com.stepping.step5.entity.Course;
import com.stepping.step5.service.model.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CoursesRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CoursesRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseOut getCourseById(int id) {
        LOGGER.debug("Getting course={}", id);

        return new CourseOut(courseRepository.findOne(id));
    }

    @Override
    public CourseOut getCourseByNumber(int number) {
        LOGGER.debug("Getting course by number={}", number);
        Course course = courseRepository.findOneByNumber(number).get();
        return new CourseOut(course);
    }

    @Override
    public Collection<CourseOut> getAllCourses() {
        LOGGER.debug("Getting all courses");
        Collection<Course> courses = courseRepository.findAll(new Sort("number"));
        Collection<CourseOut> result = new ArrayList<>();
        for (Course course:courses) {
            result.add(new CourseOut(course));
        }
        return result;
    }
/*
    @Override
    public Collection<Group> getAllCourseGroups(int number) {
        LOGGER.debug("Getting course groups, course number={}", number);
        Optional<Course> course = courseRepository.findOneByNumber(number);
        Collection<Group> groups = course.get().getGroups();
        return groups;
    }

    @Override
    public Course create(CourseCreateForm form) {
        Course course = new Course();
        course.setNumber(form.getNumb());
        return courseRepository.save(course);
    }*/
}
