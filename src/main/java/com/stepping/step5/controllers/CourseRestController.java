package com.stepping.step5.controllers;

import com.stepping.step5.entity.models.Course;
import com.stepping.step5.entity.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    private CoursesRepository coursesRepository;

    @RequestMapping("/")
    @ResponseBody
    public String getAllCourses(){
        ArrayList<Course> collection = new ArrayList<>();
        try{
            collection.addAll(coursesRepository.findAll());
        }catch (Exception ex){
            return "can't get list with courses: " + ex.toString();
        }
        String res = "";
        if (collection.size()!= 0){
            for (Course course : collection) {
                res += course.toString();
            }
            return res;
        }else return "There is no course!";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String getCourseWithId(int id){
        Course course;
        try {
            course = coursesRepository.findOne(id);
        }catch (Exception ex){
            return "Can't find course: " + ex.toString();
        }
        return course.toString();

    }


    @RequestMapping("/create")
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

    @RequestMapping("/delete")
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
