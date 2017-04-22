package com.ekey.service.impl;

import com.ekey.configs.CustomPassword;
import com.ekey.repository.GroupsRepository;
import com.ekey.repository.LibraryRepository;
import com.ekey.repository.RoleRepository;
import com.ekey.configs.MailConfig;
import com.ekey.models.Group;
import com.ekey.models.Library;
import com.ekey.models.Role;
import com.ekey.models.User;
import com.ekey.models.create.UserCreateForm;
import com.ekey.models.out.UserOut;
import com.ekey.repository.UserRepository;
import com.ekey.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    MailConfig ctx = new MailConfig();

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

        return new UserOut(userRepository.findOneByPhoneNumb(phone).get());
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
        CustomPassword pass = new CustomPassword();

        String passsword = pass.generate();

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
        try {
            JavaMailSender mailSender = ctx.javaMailSender();
            SimpleMailMessage templateMessage = new SimpleMailMessage(ctx.templateMassege());
            templateMessage.setTo(user.getUsername());
            templateMessage.setText("Your new password on E-Key is " +passsword );
            user.setPassword(new BCryptPasswordEncoder().encode(passsword));
            mailSender.send(templateMessage);
            userRepository.save(user);
        }catch (MailException ex){
            LOGGER.debug("FAILED to send message", ex.getStackTrace());
        }
        return userRepository.save(user);
    }

    @Override
    public UserOut getUserByRFID(String RFID) {
        return new UserOut(userRepository.findOneByNumberRFID(RFID).get());
    }
}
