package com.stepping.step5.service;

import com.stepping.step5.entity.models.Library;
import com.stepping.step5.entity.repository.LibraryRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("universityService")
@Transactional
public class LibraryService {

    protected static Logger logger = Logger.getLogger("service");

    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> getAllLibraries(){
        logger.debug("Retrieving all universities");

        Collection<Library> collection = new Collection<>();
        try{
            collection.addAll(libraryRepository.findAll());
        }catch (Exception ex){
            logger.debug("can't get list with libraries: " + ex.toString());
        }
        libraryRepository.f
        return collection;
    }

    public List<Library> getAllLibraries(){
        logger.debug("Retrieving all universities");

        ArrayList<Library> collection = new ArrayList<>();
        try{
            collection.addAll(libraryRepository.findAll());
        }catch (Exception ex){
            logger.debug("can't get list with libraries: " + ex.toString());
        }
        return collection;
    }

    public Library getLibraryWithId(int id){
        logger.debug("Retrieving all libraries");
        Library library = new Library();

        try {
            library = libraryRepository.findOne(id);
        }catch (Exception ex){
            logger.debug("Can't find university: " + ex.toString());
        }
        return library;

    }

    public void createLibrary(Library library){
        logger.debug("Adding new library");
        try{
            libraryRepository.save(library);
        }catch (Exception ex){
            logger.debug("Error creating the library: " + ex.toString());
        }

    }

    public void deleteLibrary(int id){
        logger.debug("Deletting existing library");
        try{
            libraryRepository.delete(libraryRepository.findOne(id));
        }catch (Exception ex)
        {logger.debug("Error deleting the library: " + ex.toString());
        }
    }

    public void editLibrary(Library library){
        logger.debug("Editing existing library");
        try{
            Library existingLibrary = libraryRepository.findOne(library.getLibraryId());
            libraryRepository.save(existingLibrary);
        }catch (Exception ex){
            logger.debug("Error editing existing library: " + ex.toString());
        }

    }

}
