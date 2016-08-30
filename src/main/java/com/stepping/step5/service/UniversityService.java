package com.stepping.step5.service;

import com.stepping.step5.entity.models.University;
import com.stepping.step5.entity.repository.UniversityRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("universityService")
@Transactional
public class UniversityService {

    protected static Logger logger = Logger.getLogger("service");

    @Autowired
    private UniversityRepository universityRepository;

    public List<University> getAllUniversities(){
        logger.debug("Retrieving all universities");

        ArrayList<University> collection = new ArrayList<>();
        try{
        collection.addAll(universityRepository.findAll());
        }catch (Exception ex){
            logger.debug("can't get list with universities: " + ex.toString());
        }
        return collection;
    }

    public University getUniversityWithId(int id){
        logger.debug("Retrieving all universities");
        University university = new University();
        university.setUniverName("null");
        try {
            university = universityRepository.findOne(id);
        }catch (Exception ex){
            logger.debug("Can't find university: " + ex.toString());
        }
        return university;

    }

    public void createUniversity(University university){
        logger.debug("Adding new university");
        try{
            universityRepository.save(university);
        }catch (Exception ex){
            logger.debug("Error creating the university: " + ex.toString());
        }

    }

    public void deleteUniversity(int id){
        logger.debug("Deletting existing university");
        try{
            universityRepository.delete(universityRepository.findOne(id));
        }catch (Exception ex)
        {logger.debug("Error deleting the university: " + ex.toString());
        }
    }

    public void editUniversity(University university){
        logger.debug("Editing existing university");
        try{
        University existingUniversity = universityRepository.findOne(university.getUniverId());
        existingUniversity.setUniverName(university.getUniverName());
        universityRepository.save(existingUniversity);
        }catch (Exception ex){
            logger.debug("Error editing existing university: " + ex.toString());
        }

    }

}
