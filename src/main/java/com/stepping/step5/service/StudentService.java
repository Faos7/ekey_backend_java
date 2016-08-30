package com.stepping.step5.service;

import com.stepping.step5.entity.models.Group;
import com.stepping.step5.entity.models.Student;
import com.stepping.step5.entity.repository.GroupsRepository;
import com.stepping.step5.entity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupsRepository groupsRepository;

    /*public String getAllStudents(){
        ArrayList<Student> collection = new ArrayList<>();
        try{
            collection.addAll(studentRepository.findAll());
        }catch (Exception ex){
            return "can't get list with students: " + ex.toString();
        }
        String res = "";
        if (collection.size()!= 0){
            for (Student student : collection) {
                res += student.toString();
            }
            return res;
        }else return "There is no student!";
    }

    public String getStudentWithId(int id){
        Student student;
        try {
            student = studentRepository.findOne(id);
        }catch (Exception ex){
            return "Can't find student: " + ex.toString();
        }
        return student.toString();

    }

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
