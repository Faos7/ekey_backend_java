package com.stepping.step5.service;

import com.stepping.step5.entity.models.Course;
import com.stepping.step5.entity.models.Group;
import com.stepping.step5.entity.models.University;
import com.stepping.step5.entity.repository.CoursesRepository;
import com.stepping.step5.entity.repository.GroupsRepository;
import com.stepping.step5.entity.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class GroupService {

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    GroupsRepository groupsRepository;

    /*public String getAllGroups(){
        ArrayList<Group> collection = new ArrayList<>();
        try{
            collection.addAll(groupsRepository.findAll());
        }catch (Exception ex){
            return "can't get list with groups: " + ex.toString();
        }
        String res = "";
        if (collection.size()!= 0){
            for (Group group : collection) {
                res += group.toString();
            }
            return res;
        }else return "There is no group!";
    }

    public String getGroupWithId(int id){
        Group group;
        try {
            group = groupsRepository.findOne(id);
        }catch (Exception ex){
            return "Can't find group: " + ex.toString();
        }
        return group.toString();

    }

    public String createGroup(String name, int cId, int uId){
        try{
            University university = universityRepository.findOne(uId);
            Course course = coursesRepository.findOne(cId);
            Group group = new Group();
            group.setGroupName(name);
            group.setUniversity(university);
            group.setCourse(course);
            university.addGroup(group);
            course.addGroup(group);
            coursesRepository.save(course);
            universityRepository.save(university);
            groupsRepository.save(group);
        }catch (Exception ex){
            return "Error creating the group: " + ex.toString();
        }
        return "Group succesfully created!";
    }

    public String deleteGroup(int id){
        try{
            Group group = groupsRepository.findOne(id);
            Course course = group.getCourse();
            University university = group.getUniversity();
            course.deleteGroup(group);
            university.deleteGroup(group);
            coursesRepository.save(course);
            universityRepository.save(university);
            groupsRepository.delete(group);
        }catch (Exception ex)
        {
            return "Error deleting the group: " + ex.toString();
        }
        return "Group succesfully deleted!";
    }

    public String getAllUniversityGroups(int id){
        ArrayList<Group> groups = new ArrayList<>();
        try{
            University university = universityRepository.findOne(id);
            groups.addAll(university.getGroups());
        }catch (Exception ex){
            return "Can't get all university groups: " + ex.toString();
        }
        String res = "";
        if (groups.size()!=0){
            for(Group group: groups){
                res += group.toString();
            }
            return res;
        }else
            return "This University has no groups!";
    }

    public String getAllCourseGroups(int id){
        ArrayList<Group> groups = new ArrayList<>();
        try{
            Course course = coursesRepository.findOne(id);
            groups.addAll(course.getGroups());
        }catch (Exception ex){
            return "Can't get all course groups: " + ex.toString();
        }
        String res = "";
        if (groups.size()!=0){
            for(Group group: groups){
                res += group.toString();
            }
            return res;
        }else return "This course has no groups!";

    }*/

}
