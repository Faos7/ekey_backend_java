package com.ekey.service;

import com.ekey.models.CurrentUser;

/**
 * Created by re5 on 24.10.16.
 */
public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
