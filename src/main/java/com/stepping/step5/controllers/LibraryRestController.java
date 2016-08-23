package com.stepping.step5.controllers;

import com.stepping.step5.entity.models.Library;
import com.stepping.step5.entity.models.University;
import com.stepping.step5.entity.repository.LibraryRepository;
import com.stepping.step5.entity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/library")
public class LibraryRestController {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @RequestMapping("/")
    @ResponseBody
    public String getAllLibraries(){
        ArrayList<Library> collection = new ArrayList<>();
        try{
            collection.addAll(libraryRepository.findAll());
        }catch (Exception ex){
            return "can't get list with libraries: " + ex.toString();
        }
        String res = "";
        if (collection.size()!= 0){
            for (Library library : collection) {
                res += library.toString();
            }
            return res;
        }else return "There is no library!";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String getLibraryWithId(int id){
        Library library;
        try {
            library = libraryRepository.findOne(id);
        }catch (Exception ex){
            return "Can't find library: " + ex.toString();
        }
        return library.toString();

    }

    @RequestMapping("/university")
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

    }


    @RequestMapping("/create")
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
