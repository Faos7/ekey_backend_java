package com.ekey.service.impl;

import com.ekey.repository.RoleRepository;
import com.ekey.models.CurrentUser;
import com.ekey.service.CurrentUserService;
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
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole().equals(roleRepository.findOne(1).getName())|| currentUser.getId().equals(userId));
    }

}
