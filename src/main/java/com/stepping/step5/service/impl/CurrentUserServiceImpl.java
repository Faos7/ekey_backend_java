package com.stepping.step5.service.impl;

import com.stepping.step5.models.CurrentUser;
import com.stepping.step5.repository.RoleRepository;
import com.stepping.step5.service.CurrentUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by re5 on 24.10.16.
 */

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Integer userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == roleRepository.findOneByName("ADMIN")|| currentUser.getId().equals(userId));
    }

}
