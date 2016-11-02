package com.stepping.step5.service;

import com.stepping.step5.models.out.GroupOut;

import java.util.Collection;

/**
 * Created by re5 on 02.11.16.
 */
public interface GroupService {

    GroupOut getGroupById(Integer id);

    Collection<GroupOut> getAllGroups();

    Collection<GroupOut> getAllFacultyGroups(Integer id);
}
