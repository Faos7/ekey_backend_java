package com.stepping.step5.controllers;

import com.stepping.step5.entity.models.Library;
import com.stepping.step5.entity.models.University;
import com.stepping.step5.entity.repository.LibraryRepository;
import com.stepping.step5.entity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/library")
public class LibraryRestController {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Library>> getAllLibraries(){
        return new ResponseEntity<>((Collection<Library>) libraryRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Library> getLibraryWithId(@PathVariable int id){
        return new ResponseEntity<>(libraryRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createLibrary(int univerId){
        try{
            Library library = new Library();
            University university = universityRepository.findOne(univerId);
            library.setUniversity(university);
            university.addLibrary(library);
            libraryRepository.save(library);
            universityRepository.save(university);
        }catch (Exception ex){
            return "Error creating the university: " + ex.toString();
        }
        return "Library succesfully created!";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteLibrary(int id){
        try{
            Library library = libraryRepository.findOne(id);
            University university = library.getUniversity();
            university.deleteLibrary(library);
            universityRepository.save(university);
            libraryRepository.delete(library);
        }catch (Exception ex)
        {return "Error deleting the university: " + ex.toString();
        }
        return "Library succesfully deleted!";
    }
}
