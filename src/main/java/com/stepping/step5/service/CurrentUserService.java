package com.stepping.step5.service;

import com.stepping.step5.models.CurrentUser;

/**
 * Created by re5 on 24.10.16.
 */
public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Integer userId);
}
