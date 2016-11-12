package com.stepping.step5.controller;

import com.stepping.step5.models.Course;
import com.stepping.step5.models.Faculty;
import com.stepping.step5.models.Group;
import com.stepping.step5.repository.CoursesRepository;
import com.stepping.step5.repository.FacultyRepository;
import com.stepping.step5.repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/** {@link Group} REST controller
 *
 * @author faos7
 * @version 1.2
 */

@RestController
@RequestMapping("/group")
public class GroupRestController {

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    GroupsRepository groupsRepository;

    /**
     * Get all groups
     * @return Json with all {@link Group}s
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Group>> getAllGroops(){
        return new ResponseEntity<>((Collection<Group>) groupsRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Group> getGroupWithId(@PathVariable int id){
        return new ResponseEntity<>(groupsRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createGroup(String name, int cId, int fId){
        try{
            Faculty faculty = facultyRepository.findOne(fId);
            Course course = coursesRepository.findOne(cId);
            Group group = new Group();
            group.setName(name);
            group.setFaculty(faculty);
            group.setCourse(course);
            faculty.addGroup(group);
            course.addGroup(group);
            coursesRepository.save(course);
            facultyRepository.save(faculty);
            groupsRepository.save(group);
        }catch (Exception ex){
            return "Error creating the group: " + ex.toString();
        }
        return "Group succesfully created!";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteGroup(int id){
        try{
            Group group = groupsRepository.findOne(id);
            Course course = group.getCourse();
            Faculty faculty = group.getFaculty();
            course.deleteGroup(group);
            faculty.deleteGroup(group);
            coursesRepository.save(course);
            facultyRepository.save(faculty);
            groupsRepository.delete(group);
        }catch (Exception ex)
        {
            return "Error deleting the group: " + ex.toString();
        }
        return "Group succesfully deleted!";
    }

    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.GET)
    @ResponseBody
        public ResponseEntity<Collection<Group>> getAllFacultyGroups(@PathVariable int id){
            Faculty faculty = facultyRepository.findOne(id);
            return new ResponseEntity<Collection<Group>>(faculty.getGroups(), HttpStatus.OK);
        }

        @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
        @ResponseBody
        public ResponseEntity<Collection<Group>> getAllCourseGroups(@PathVariable int id){
            Course course = coursesRepository.findOne(id);
            return new ResponseEntity<Collection<Group>>(course.getGroups(), HttpStatus.OK);
    }

}
