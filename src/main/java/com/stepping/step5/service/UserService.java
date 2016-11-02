package com.stepping.step5.service;

import com.stepping.step5.models.out.UserOut;

import java.util.Collection;

/**
 * Created by faos7 on 27.10.16.
 */
public interface UserService {

    UserOut getUserById(Long id);

    UserOut getUserByUsername(String username);

    Collection<UserOut> getAllUsers();

    Collection<UserOut> getAllUsersWithSpecifiedRole(String role);

    Collection<UserOut> getAllGroupStudents(int id);

    Collection<UserOut> getAllLibrarians(int id);
}
