package com.stepping.step5.controller;

import com.stepping.step5.entity.Faculty;
import com.stepping.step5.entity.University;
import com.stepping.step5.repository.FacultyRepository;
import com.stepping.step5.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by re5 on 20.10.16.
 */

@RestController
@RequestMapping("/faculty")
public class FacultyRestController {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Faculty>> getAllFaculties(){
        return new ResponseEntity<>((Collection<Faculty>) facultyRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Faculty> getFacultyWithId(@PathVariable int id){
        return new ResponseEntity<>(facultyRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Faculty>> getAllUniversityFaculties(@PathVariable int id){
        University university = universityRepository.findOne(id);
        return new ResponseEntity<Collection<Faculty>>(university.getFaculties(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createFaculty(int id){
        try{
            Faculty faculty = new Faculty();
            University university = universityRepository.findOne(id);
            faculty.setUniversity(university);
            university.addFaculty(faculty);
            facultyRepository.save(faculty);
            universityRepository.save(university);
        }catch (Exception ex){
            return "Error creating the faculty: " + ex.toString();
        }
        return "Library succesfully created!";
    }

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
        return "Faculty succesfully deleted!";
    }
}
