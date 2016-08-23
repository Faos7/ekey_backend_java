package com.stepping.step5.controllers;

import com.stepping.step5.entity.models.University;
import com.stepping.step5.entity.repository.UniversityRepository;
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<University>> getAllUniversities(){
        return new ResponseEntity<>((Collection<University>) universityRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<University> getUniversityWithId(@PathVariable int id){
        return new ResponseEntity<>(universityRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createUniversity(String name){
        try{
            University university = new University();
            university.setUniverName(name);
            universityRepository.save(university);
        }catch (Exception ex){
            return "Error creating the university: " + ex.toString();
        }
        return "University succesfully created!";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteUniversity(int id){
        try{
            universityRepository.delete(universityRepository.findOne(id));
        }catch (Exception ex)
        {return "Error deleting the university: " + ex.toString();
        }
        return "University succesfully deleted!";
    }

    /*@RequestMapping("/get")
    @ResponseBody
    public String getUniversity(String name){
        String univerId="";
        try {
            University university = universityRepository.findByName(name);
            univerId = String.valueOf(university.getUniverId());
        }catch (Exception ex){
            return "University not found";
        }
        return "University id is: " + univerId;
    }*/

}
