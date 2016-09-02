package com.stepping.step5.controller;


import com.stepping.step5.entity.models.Course;
import com.stepping.step5.entity.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    private CoursesRepository coursesRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Course>> getAllCourses(){
        return new ResponseEntity<>((Collection<Course>) coursesRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Course> getCourseWithId(@PathVariable int id){
        return new ResponseEntity<>(coursesRepository.findOne(id), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createCourse(int numb){
        try{
            Course course = new Course();
            course.setCourseNumb(numb);
            coursesRepository.save(course);
        }catch (Exception ex){
            return "Error creating the course: " + ex.toString();
        }
        return "Course succesfully created!";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCourse(int id){
        try{
            coursesRepository.delete(coursesRepository.findOne(id));
        }catch (Exception ex)
        {return "Error deleting the course: " + ex.toString();
        }
        return "Course succesfully deleted!";
    }
}