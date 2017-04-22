package com.ekey.controller;

import com.ekey.models.Course;
import com.ekey.models.Group;
import com.ekey.repository.CoursesRepository;
import com.ekey.repository.FacultyRepository;
import com.ekey.repository.GroupsRepository;
import com.ekey.models.Faculty;
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

    /**
     * get {@link Group} with {@param id}
     * @param id - {@link Group}'s id
     * @return Json with {@link Group} which has {@param id}
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Group> getGroupWithId(@PathVariable int id){
        return new ResponseEntity<>(groupsRepository.findOne(id), HttpStatus.OK);
    }

    /**
     * Create {@link Group} and add it to database
     * @param name - {@link Group} name
     * @param cId - {@link Course} - id of {@link Course} to which {@link Group} is bounded
     * @param fId - {@link Faculty} - id of {@link Faculty} to which {@link Faculty} is bounded
     * @return String. if string is equal to "Transaction success" then {@link Group}
     * is added to database. else - error
     */
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
        return "Transaction success";
    }

    /**
     * Delete {@link Group} from database
     * @param id - {@link Group} id
     * @return String. if string is equal to "Transaction success" then {@link Group}
     * is deleted from database. else - error
     */
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
        return "Transaction success";
    }

    /**
     * get all {@link Faculty} {@link Group}s
     * @param id - {@link Faculty} id
     * @return Json with all {@link Group} which are bounded to {@link Faculty}
     */
    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Group>> getAllFacultyGroups(@PathVariable int id){
        Faculty faculty = facultyRepository.findOne(id);
        return new ResponseEntity<Collection<Group>>(faculty.getGroups(), HttpStatus.OK);
    }

    /**
     * get all {@link Course} {@link Group}s
     * @param id - {@link Course}
     * @return Json with all {@link Group} which are bounded to {@link Course}
     */
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Group>> getAllCourseGroups(@PathVariable int id){
        Course course = coursesRepository.findOne(id);
        return new ResponseEntity<Collection<Group>>(course.getGroups(), HttpStatus.OK);
    }

}
