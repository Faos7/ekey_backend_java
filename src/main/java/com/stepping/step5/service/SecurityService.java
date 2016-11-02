package com.stepping.step5.service;

/**
 * Service for Security.
 *
 * @author Faost
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
