package com.stepping.step5.controller;


import com.stepping.step5.entity.Course;
import com.stepping.step5.models.CourseOut;
import com.stepping.step5.repository.CoursesRepository;
import com.stepping.step5.service.model.CourseService;
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

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CourseOut>> getAllCourses(){
        return new ResponseEntity<>((Collection<CourseOut>) courseService.getAllCourses(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CourseOut> getCourseWithId(@PathVariable int id){
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createCourse(int numb){
        try{
            Course course = new Course();
            course.setNumber(numb);
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
