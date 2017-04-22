package com.ekey.service;

import com.ekey.models.User;
import com.ekey.models.create.UserCreateForm;
import com.ekey.models.out.UserOut;

import java.util.Collection;

/**
 * Created by faos7 on 27.10.16.
 */
public interface UserService {

    UserOut getUserByRFID(String RFID);

    UserOut getUserById(Long id);

    UserOut getUserByPhone(Long phone);

    UserOut getUserByEmail(String email);

    Collection<UserOut> getAllUsers();

    Collection<UserOut> getAllUsersWithSpecifiedRole(String role);

    Collection<UserOut> getAllGroupStudents(int id);

    Collection<UserOut> getAllLibrarians(int id);

    User create(UserCreateForm form);
}
