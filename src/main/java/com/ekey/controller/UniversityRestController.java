package com.ekey.controller;

import com.ekey.service.UniversityService;
import com.ekey.models.University;
import com.ekey.models.out.UniversityOut;
import com.ekey.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 * {@link University} REST controller
 *
 * @author faos7
 * @version 1.0
 */
@RestController
@RequestMapping("/university")
public class UniversityRestController {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private UniversityService universityService;

    /**
     * Get all universities
     * @return Json with all {@link University}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<UniversityOut>> getAllUniversities(){
        return new ResponseEntity<>((Collection<UniversityOut>) universityService.getAllUniversities(), HttpStatus.OK);
    }

    /**
     * get university with id
     * @param id - {@link University} id
     * @return Json with {@link University} which has id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<University> getUniversityWithId(@PathVariable int id){
        return new ResponseEntity<>(universityRepository.findOne(id), HttpStatus.OK);
    }

    /**
     * Create {@link University} with {@param name}
     * @param name - {@link} University name
     * @return String. if string is equal to "Transaction success" then {@link University}
     * is added to database. else - error
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createUniversity(String name){
        try{
            University university = new University();
            university.setName(name);
            universityRepository.save(university);
        }catch (Exception ex){
            return "Error creating the university: " + ex.toString();
        }
        return "Transaction success";
    }

    /**
     * delete {@link University} with id
     * @param id - {@link University} id
     * @return String. if string is equal to "Transaction success" then {@link University}
     * is deleted from database. else - error
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUniversity(int id){
        try{
            universityRepository.delete(universityRepository.findOne(id));
        }catch (Exception ex)
        {return "Error deleting the university: " + ex.toString();
        }
        return "Transaction success";
    }

}
