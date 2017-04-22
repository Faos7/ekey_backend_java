package com.ekey.controller;

import com.ekey.models.Faculty;
import com.ekey.models.University;
import com.ekey.repository.FacultyRepository;
import com.ekey.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * {@link Faculty} REST controller
 *
 * @author faos7
 * @version 1.2
 */

@RestController
@RequestMapping("/faculty")
public class FacultyRestController {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UniversityRepository universityRepository;

    /**
     * Get all {@link Faculty}
     * @return Json with all{@link Faculty}s
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Faculty>> getAllFaculties(){
        return new ResponseEntity<>((Collection<Faculty>) facultyRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Get {@link Faculty} with {@param id}
     * @param id - {@link Faculty} id
     * @return Json with {@link Faculty} which has id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Faculty> getFacultyWithId(@PathVariable int id){
        return new ResponseEntity<>(facultyRepository.findOne(id), HttpStatus.OK);
    }

    /**
     * Get all university faculties
     * @param id - {@link University} id
     * @return Json with all {@link Faculty}s
     */
    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Faculty>> getAllUniversityFaculties(@PathVariable int id){
        University university = universityRepository.findOne(id);
        return new ResponseEntity<Collection<Faculty>>(university.getFaculties(), HttpStatus.OK);
    }

    /**
     * Create {@link Faculty} with {@param name} and add it to database
     * @param uName - {@link University} name
     * @param name - {@link Faculty} name
     * @return String. if string is equal to "Transaction success" then {@link Faculty}
     * is added to database. else - error
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createFaculty(String uName, String name){
        try{
            Faculty faculty = new Faculty();
            University university = universityRepository.findOneByName(uName).get();
            faculty.setUniversity(university);
            faculty.setName(name);
            university.addFaculty(faculty);
            facultyRepository.save(faculty);
            universityRepository.save(university);
        }catch (Exception ex){
            return "Error creating the faculty: " + ex.toString();
        }
        return "Transaction success";
    }

    /**
     * Delete {@link Faculty} with {@param id} from database
     * @param id - {@link Faculty} id
     * @return String. if string is equal to "Transaction success" then {@link Faculty}
     * is deleted from database. else - error
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFaculty(int id){
        try{
            Faculty faculty = facultyRepository.findOne(id);
            University university = faculty.getUniversity();
            university.deleteFaculty(faculty);
            universityRepository.save(university);
            facultyRepository.delete(faculty);
        }catch (Exception ex)
        {return "Error deleting the faculty: " + ex.toString();
        }
        return "Transaction success";
    }
}
