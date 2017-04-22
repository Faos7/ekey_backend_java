package com.ekey.service;

import com.ekey.models.out.GroupOut;

import java.util.Collection;

/**
 * Created by re5 on 02.11.16.
 */
public interface GroupService {

    GroupOut getGroupById(Integer id);

    Collection<GroupOut> getAllGroups();

    Collection<GroupOut> getAllFacultyGroups(Integer id);
}
