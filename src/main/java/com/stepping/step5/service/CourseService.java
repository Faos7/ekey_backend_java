package com.stepping.step5.service;

import com.stepping.step5.entity.models.Course;
import com.stepping.step5.entity.repository.CoursesRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("courseService")
@Transactional
public class CourseService {

    protected static Logger logger = Logger.getLogger("service");

    @Autowired
    private CoursesRepository coursesRepository;

    public List<Course> getAllCourses(){
        logger.debug("Retrieving all courses");

        ArrayList<Course> collection = new ArrayList<>();
        try{
            collection.addAll(coursesRepository.findAll());
        }catch (Exception ex){
            logger.debug("can't get list with courses: " + ex.toString());
        }
        return collection;
    }

    public Course getCourseWithId(int id){
        logger.debug("Retrieving all courses");
        Course course = new Course();
        course.setCourseNumb(-1);
        try {
            course = coursesRepository.findOne(id);
        }catch (Exception ex){
            logger.debug("Can't find course: " + ex.toString());
        }
        return course;

    }

    public void createCourse(Course course){
        logger.debug("Adding new course");
        try{
            coursesRepository.save(course);
        }catch (Exception ex){
            logger.debug("Error creating the course: " + ex.toString());
        }

    }

    public void deleteCourse(int id){
        logger.debug("Deletting existing course");
        try{
            coursesRepository.delete(coursesRepository.findOne(id));
        }catch (Exception ex)
        {logger.debug("Error deleting the course: " + ex.toString());
        }
    }

    public void editCourse(Course course){
        logger.debug("Editing existing university");
        try{
            Course existingCourse = coursesRepository.findOne(course.getCourseId());
            existingCourse.setCourseNumb(course.getCourseNumb());
            coursesRepository.save(existingCourse);
        }catch (Exception ex){
            logger.debug("Error editing existing university: " + ex.toString());
        }

    }

}
