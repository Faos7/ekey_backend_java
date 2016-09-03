package com.stepping.step5.controller;

import com.stepping.step5.models.Group;
import com.stepping.step5.models.Library;
import com.stepping.step5.models.Role;
import com.stepping.step5.models.User;
import com.stepping.step5.repository.GroupsRepository;
import com.stepping.step5.repository.LibraryRepository;
import com.stepping.step5.repository.RoleRepository;
import com.stepping.step5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * Created by re5 on 20.10.16.
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupsRepository groupsRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> getAllUsers(){
        return new ResponseEntity<>((Collection<User>) userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{role}")
    public ResponseEntity<Collection<User>> getAllUsersWithRole(@PathVariable String role){
        return new ResponseEntity<>((Collection<User>) userRepository.findManyByRole(roleRepository.findOneByName(role)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> getUserWithId(@PathVariable Long id){
        return new ResponseEntity<>(userRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createUser(String firstName, String secondName, String thirdName, int id,
                                String username, String password, long phone, String role){
        try{
            User user = new User();
            Role role1= roleRepository.findOneByName(role);
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setThirdName(thirdName);
            user.setUsername(username);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setPhoneNumb(phone);
            user.setRole(role1);
            if (role1.equals(roleRepository.findOneByName("LIBRARIAN"))){
                Library library = libraryRepository.findOne(id);
                user.setLibrary(library);
                library.addLibrarian(user);
                libraryRepository.save(library);
            }
            else if (role1.equals(roleRepository.findOneByName("STUDENT"))){
                Group group = groupsRepository.findOne(id);
                user.setGroup(group);
                group.addStudent(user);
                groupsRepository.save(group);
            }
            userRepository.save(user);
        }catch (Exception ex){
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created!";
    }
/*
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
*/
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<User>> getAllGroupStudents(@PathVariable int id){
        return new ResponseEntity<Collection<User>>(groupsRepository.findOne(id).getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/library/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<User>> getAllLibraryLibrarians(@PathVariable int id){
        return new ResponseEntity<Collection<User>>(libraryRepository.findOne(id).getUsers(), HttpStatus.OK);
    }
}

