package com.stepping.step5.controller;

import com.stepping.step5.entity.models.Group;
import com.stepping.step5.entity.models.Student;
import com.stepping.step5.entity.repository.GroupsRepository;
import com.stepping.step5.entity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupsRepository groupsRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Student>> getAllStudents(){
        return new ResponseEntity<>((Collection<Student>) studentRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Student> getStudentWithId(@PathVariable int id){
        return new ResponseEntity<>(studentRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createStudent(String name1, String name2, String name3,
                                String username, String password, int id, long phone){
        try{
            Student student = new Student();
            Group group = groupsRepository.findOne(id);
            student.setFirstName(name1);
            student.setSecondName(name2);
            student.setPoBatkovy(name3);
            student.setUsername(username);
            student.setPassword(password);
            student.setPhoneNumb(phone);
            student.setGroup(group);
            group.addStudent(student);
            groupsRepository.save(group);
            studentRepository.save(student);
        }catch (Exception ex){
            return "Error creating the student: " + ex.toString();
        }
        return "Student succesfully created!";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteStudent(int id){
        try{
            Student student =studentRepository.findOne(id);
            Group group = student.getGroup();
            group.deleteStudent(student);
            groupsRepository.save(group);
            studentRepository.delete(student);
        }catch (Exception ex)
        {
            return "Error deleting the student: " + ex.toString();
        }
        return "Student succesfully deleted!";
    }

    /*@RequestMapping("/group")
    @ResponseBody
    public String getAllGroupStudents(int id){
        ArrayList<Student> students = new ArrayList<>();
        try{
            Group group = groupsRepository.findOne(id);
            students.addAll(group.getStudents());
        }catch (Exception ex){
            return "Can't get all group students: " + ex.toString();
        }
        String res = "";
        if (students.size()!=0){
            for(Student student: students){
                res += student.toString();
            }
            return res;
        }else
            return "This group has no students!";
    }*/
}