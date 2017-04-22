package com.ekey.controller;

import com.ekey.models.Book;
import com.ekey.repository.GroupsRepository;
import com.ekey.repository.LibraryRepository;
import com.ekey.repository.RoleRepository;
import com.ekey.models.Group;
import com.ekey.models.Library;
import com.ekey.models.Role;
import com.ekey.models.User;
import com.ekey.models.create.UserCreateForm;
import com.ekey.models.out.UserOut;
import com.ekey.repository.UserRepository;
import com.ekey.service.SecurityService;
import com.ekey.service.TransactionService;
import com.ekey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * {@link User} REST controller
 *
 * @author faos7
 * @version 1.2
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupsRepository groupsRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    SecurityService securityService;

    /**
     * Get all {@link User}s
     *
     * @return Json with all {@link User}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<UserOut>> getAllUsers(){
        return new ResponseEntity<>((Collection<UserOut>) userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * Get all {@link User}s with {@param role}
     * @param role - {@link User} role
     * @return Json with all {@link User}s who's role is {@param role}
     */
    @RequestMapping(method = RequestMethod.GET, value = "/role/{role}")
    public ResponseEntity<Collection<UserOut>> getAllUsersWithRole(@PathVariable String role){
        return new ResponseEntity<>((Collection<UserOut>) userService.getAllUsersWithSpecifiedRole(role), HttpStatus.OK);
    }

    /**
     * Get {@link User} with {@param id}
     * @param id - {@link User} Id
     * @return Json with {@link User} which has specified id
     */
    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserOut> getUserWithId(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    /**
     * Get {@link User} with {@param username}
     * @param username - {@link User} username
     * @return Json with {@link User} which has specified username
     */
    @RequestMapping(method = RequestMethod.GET, value = "/username/{username}")
    public ResponseEntity<UserOut> getUserWithUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.getUserByEmail(username), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/lg")
    public String userLogin(String username, String password){
        securityService.autoLogin(username, password);
        return "sucessfuly logged in!";
    }

    /**
     * Create {@link User} and add him to database
     * @param firstName - {@link User}s first name
     * @param secondName - {@link User}s second name
     * @param thirdName - {@link User}s third name
     //* @param id - if role is STUDENT this id is a {@link Group} id, student studies in
     *           if role is LIBRARIAN this id is a {@link Library} id, librarian works in
     * @param username - {@link User}s username
     //* @param password - {@link User} password
     * @param phone - {@link User} phone number
     * @param role - {@link User} role
     * @return String. if string is equal to "Transaction success" then {@link User}
     * is added to database. else - error
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createUser(String firstName, String thirdName, String secondName,
                             String username, long phone, String numberRFID ,  String role,
                             String university, String faculty, String Group, String library){
        try{
            UserCreateForm user = new UserCreateForm();
            Role role1 = roleRepository.findOneByName(role);

            user.setEmail(username);
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setThirdName(thirdName);
            /*user.setPassword(password);*/
            user.setPhoneNumb(phone);
            user.setRole(role1);
            user.setNumberRFID(numberRFID);
            user.setUniversity(university);

/*
            if (role1.equals(roleRepository.findOneByName("LIBRARIAN"))){
                user.setId(id);
            }
            else if (role1.equals(roleRepository.findOneByName("STUDENT"))){
                user.setId(id);
            }*/
            userService.create(user);
            /*
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
            }*/
            //userRepository.save(user);
        }catch (Exception ex){
            return "Error creating the user: " + ex.toString();
        }
        return "Transaction success";
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

    /**
     * get all {@link User} students in group
     * @param id - {@link User} students id
     * @return Json with all {@link User} students in {@link Group}
     */
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<UserOut>> getAllGroupStudents(@PathVariable int id){
        return new ResponseEntity<Collection<UserOut>>(userService.getAllGroupStudents(id), HttpStatus.OK);
    }

    /**
     * Get all {@link Book} owners
     * @param number {@link Book} - inventory number
     * @return Json with all {@link User} students who had owned the {@link Book}
     */
    @RequestMapping(value = "/book/{number}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<UserOut>> getAllBookOwners(@PathVariable String number){
        return new ResponseEntity<Collection<UserOut>>(transactionService.getAllBookOwners(number), HttpStatus.OK);
    }

    /**
     * Get all {@link User} librarians in {@link Library}
     * @param id - {@link Library} id
     * @return Json with all {@link User} librarians who work in {@link Library} with id
     */
    @RequestMapping(value = "/library/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<UserOut>> getAllLibraryLibrarians(@PathVariable int id){
        return new ResponseEntity<Collection<UserOut>>(userService.getAllLibrarians(id), HttpStatus.OK);
    }
}
