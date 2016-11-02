package com.stepping.step5.controller;

import com.stepping.step5.models.University;
import com.stepping.step5.models.out.UniversityOut;
import com.stepping.step5.repository.UniversityRepository;
import com.stepping.step5.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/university")
public class UniversityRestController {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private UniversityService universityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<UniversityOut>> getAllUniversities(){
        return new ResponseEntity<>((Collection<UniversityOut>) universityService.getAllUniversities(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<University> getUniversityWithId(@PathVariable int id){
        return new ResponseEntity<>(universityRepository.findOne(id), HttpStatus.OK);
    }

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
        return "University succesfully created!";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUniversity(int id){
        try{
            universityRepository.delete(universityRepository.findOne(id));
        }catch (Exception ex)
        {return "Error deleting the university: " + ex.toString();
        }
        return "University succesfully deleted!";
    }

}
