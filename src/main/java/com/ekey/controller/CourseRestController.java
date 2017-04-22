package com.ekey.controller;


import com.ekey.models.Course;
import com.ekey.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * {@link Course} REST controller
 *
 * @author faos7
 * @version 1.1
 */

@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    private CoursesRepository coursesRepository;

    /**
     * Get all courses
     * @return Json with all {@link Course}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Course>> getAllCourses(){
        return new ResponseEntity<>((Collection<Course>) coursesRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Get course wih id
     * @param id - {@link Course} id
     * @return Json with {@link Course} which has id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Course> getCourseWithId(@PathVariable int id){
        return new ResponseEntity<>(coursesRepository.findOne(id), HttpStatus.OK);
    }

}
