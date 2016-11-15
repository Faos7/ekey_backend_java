package com.stepping.step5.service.impl;

import com.stepping.step5.models.Group;
import com.stepping.step5.models.Library;
import com.stepping.step5.models.Role;
import com.stepping.step5.models.User;
import com.stepping.step5.models.create.UserCreateForm;
import com.stepping.step5.models.out.UserOut;
import com.stepping.step5.repository.GroupsRepository;
import com.stepping.step5.repository.LibraryRepository;
import com.stepping.step5.repository.RoleRepository;
import com.stepping.step5.repository.UserRepository;
import com.stepping.step5.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by faos7 on 27.10.16.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GroupsRepository groupsRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           GroupsRepository groupsRepository, LibraryRepository libraryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.groupsRepository = groupsRepository;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public UserOut getUserById(Long id) {
        LOGGER.debug("Getting user={}", id);

        return new UserOut(userRepository.findOne(id));
    }

    @Override
    public UserOut getUserByPhone(Long phone) {
        LOGGER.debug("Getting user by phone number={}", phone);

        return new UserOut(userRepository.findOneByPoneNumb(phone).get());
    }

    @Override
    public UserOut getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return new UserOut(userRepository.findOneByUsername(email));
    }

    @Override
    public Collection<UserOut> getAllUsers() {
        LOGGER.debug("Getting all users");
        Collection<User> users = userRepository.findAll(new Sort("username"));
        Collection<UserOut> result = new ArrayList<>();
        for (User user: users){
            result.add(new UserOut(user));
        }
        return result;
    }

    @Override
    public Collection<UserOut> getAllUsersWithSpecifiedRole(String role) {
        LOGGER.debug("Getting all users with role={}", role);
        Role role1 = roleRepository.findOneByName(role);
        Collection<User> users = role1.getUsers();
        Collection<UserOut> result = new ArrayList<>();
        for (User user: users){
            result.add(new UserOut(user));
        }
        return result;
    }

    @Override
    public Collection<UserOut> getAllGroupStudents(int id) {
        LOGGER.debug("Getting all users with group={}", id);
        Group group = groupsRepository.findOne(id);
        Collection<User> users = group.getUsers();
        Collection<UserOut> result = new ArrayList<>();
        for (User user: users){
            result.add(new UserOut(user));
        }
        return result;
    }

    @Override
    public Collection<UserOut> getAllLibrarians(int id) {
        LOGGER.debug("Getting all users with library={}", id);
        Library library = libraryRepository.findOne(id);
        Collection<User> users = library.getUsers();
        Collection<UserOut> result = new ArrayList<>();
        for (User user: users){
            result.add(new UserOut(user));
        }
        return result;
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setUsername(form.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        if (user.getRole().equals(roleRepository.findOneByName("LIBRARIAN"))){
            Library library = libraryRepository.findOne(form.getId());
            user.setLibrary(library);
            library.addLibrarian(user);
            libraryRepository.save(library);
        }
        else if (user.getRole().equals(roleRepository.findOneByName("STUDENT"))){
            Group group = groupsRepository.findOne(form.getId());
            user.setGroup(group);
            group.addStudent(user);
            groupsRepository.save(group);
        }
        userRepository.save(user);
        return userRepository.save(user);
    }
}
