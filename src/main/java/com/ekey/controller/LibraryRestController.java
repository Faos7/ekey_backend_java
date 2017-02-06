package com.ekey.controller;

import com.ekey.repository.LibraryRepository;
import com.ekey.models.Library;
import com.ekey.models.University;
import com.ekey.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * {@link Library} REST controller
 *
 * @author faos7
 * @version 1.2
 */
@RestController
@RequestMapping("/library")
public class LibraryRestController {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UniversityRepository universityRepository;

    /**
     * Get all {@link Library}s
     * @return Json with all {@link Library}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Library>> getAllLibraries(){
        return new ResponseEntity<>((Collection<Library>) libraryRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Get {@link Library} with {@param id}
     * @param id - {@link Library} id
     * @return Json with {@link Library} which has specified id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Library> getlibraryWithId(@PathVariable int id){
        return new ResponseEntity<>(libraryRepository.findOne(id), HttpStatus.OK);
    }

    /**
     * Get all {@link University} {@link Library}s
     * @param id - {@link University} id
     * @return Json with all {@link University} {@link Library}s
     */
    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Library>> getAllUniversityLibraries(@PathVariable int id){
        University university = universityRepository.findOne(id);
        return new ResponseEntity<Collection<Library>>(university.getLibraries(), HttpStatus.OK);
    }

    /**
     * Create {@link Library}  wich is bounded to {@link University} with {@param id}
     * @param id - {@link University} id
     * @return String. if string is equal to "Transaction success" then {@link Library}
     * is added to database. else - error
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createLibrary(int id){
        try{
            Library library = new Library();
            University university = universityRepository.findOne(id);
            library.setUniversity(university);
            university.addLibrary(library);
            libraryRepository.save(library);
            universityRepository.save(university);
        }catch (Exception ex){
            return "Error creating the university: " + ex.toString();
        }
        return "Library succesfully created!";
    }

    /**
     * Delete {@link Library} from database
     * @param id {@link Library} id
     * @return String. if string is equal to "Transaction success" then {@link Library}
     * is deleted from database. else - error
     */
    @RequestMapping(method = RequestMethod.DELETE)
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
