package com.stepping.step5.controller;

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
    public ResponseEntity<Library> getlibraryWithId(@PathVariable int id){
        return new ResponseEntity<>(libraryRepository.findOne(id), HttpStatus.OK);
    }

    /*@RequestMapping("/university")
    @ResponseBody
    public String getAllUniversityLibraries(int id){
        ArrayList<Library> libraries = new ArrayList<>();
        try{
        University university = universityRepository.findOne(id);
            libraries.addAll(university.getLibraries());
        }catch (Exception ex){
            return "Can't get all university libraries: " + ex.toString();
        }
        String res = "";
        if (libraries.size()!=0){
            for(Library library: libraries){
                res += library.toString();
            }
            return res;
        }else return "This University has no libraries!";
    }*/


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