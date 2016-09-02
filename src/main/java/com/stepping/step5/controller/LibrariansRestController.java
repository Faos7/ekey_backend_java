package com.stepping.step5.controller;

import com.stepping.step5.entity.models.Librarian;
import com.stepping.step5.entity.models.Library;
import com.stepping.step5.entity.repository.LibrariansRepository;
import com.stepping.step5.entity.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/librarian")
public class LibrariansRestController {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibrariansRepository librariansRepository;


    /*@RequestMapping(value = "/library", method = RequestMethod.GET)
    @ResponseBody
    public String getAllLibraryLibrarians(int id){
        ArrayList<Librarian> librarians = new ArrayList<>();
        try{
            Library library = libraryRepository.findOne(id);
            librarians.addAll(library.getLibrarien());
        }catch (Exception ex){
            return "Can't get all library librarians: " + ex.toString();
        }
        String res = "";
        if (librarians.size()!=0){
            for(Librarian librarian: librarians){
                res += librarian.toString();
            }
            return res;
        }else return "This library has no librarians!";
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Librarian>> getAllLibrarians(){
        return new ResponseEntity<>((Collection<Librarian>) librariansRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Librarian> getlibrarianWithId(@PathVariable int id){
        return new ResponseEntity<>(librariansRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createLibrarian(String name1, String name2, String name3, int id){
        try{
            Library library = libraryRepository.getOne(id);
            Librarian librarian = new Librarian();
            librarian.setFirstName(name1);
            librarian.setSecondName(name2);
            librarian.setPoBatkyovy(name3);
            librarian.setLibrary(library);
            libraryRepository.save(library);
            librariansRepository.save(librarian);
        }catch (Exception ex){
            return "Error creating the librarian: " + ex.toString();
        }
        return "Librarian succesfully created!";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteLibrarian(int id){
        try{
            Librarian librarian = librariansRepository.findOne(id);
            Library library = librarian.getLibrary();
            library.deleteLibrarian(librarian);
            libraryRepository.save(library);
            librariansRepository.delete(librarian);
        }catch (Exception ex)
        {return "Error deleting the university: " + ex.toString();
        }
        return "Library succesfully deleted!";
    }
}