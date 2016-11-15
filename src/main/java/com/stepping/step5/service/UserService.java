package com.stepping.step5.service;

import com.stepping.step5.models.User;
import com.stepping.step5.models.create.UserCreateForm;
import com.stepping.step5.models.out.UserOut;

import java.util.Collection;

/**
 * Created by faos7 on 27.10.16.
 */
public interface UserService {

    UserOut getUserById(Long id);

    UserOut getUserByPhone(Long phone);

    UserOut getUserByEmail(String email);

    Collection<UserOut> getAllUsers();

    Collection<UserOut> getAllUsersWithSpecifiedRole(String role);

    Collection<UserOut> getAllGroupStudents(int id);

    Collection<UserOut> getAllLibrarians(int id);

    User create(UserCreateForm form);
}
