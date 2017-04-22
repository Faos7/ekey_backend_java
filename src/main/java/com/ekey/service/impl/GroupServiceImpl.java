package com.ekey.service.impl;

import com.ekey.models.Group;
import com.ekey.models.out.GroupOut;
import com.ekey.repository.FacultyRepository;
import com.ekey.repository.GroupsRepository;
import com.ekey.models.Faculty;
import com.ekey.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by re5 on 02.11.16.
 */
@Service
public class GroupServiceImpl implements GroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupsRepository groupsRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public GroupServiceImpl(GroupsRepository groupsRepository, FacultyRepository facultyRepository) {
        this.groupsRepository = groupsRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public GroupOut getGroupById(Integer id) {
        LOGGER.debug("Getting group={}", id);

        return new GroupOut(groupsRepository.findOne(id));
    }

    @Override
    public Collection<GroupOut> getAllGroups() {
        LOGGER.debug("Getting all groups");
        Collection<Group> groups = groupsRepository.findAll(new Sort("name"));
        Collection<GroupOut> result = new ArrayList<>();
        for (Group group: groups){
            result.add(new GroupOut(group));
        }
        return result;
    }

    @Override
    public Collection<GroupOut> getAllFacultyGroups(Integer id) {
        LOGGER.debug("Getting all groups with faculty={}", id);
        Faculty faculty = facultyRepository.findOne(id);
        Collection<Group> groups = faculty.getGroups();
        Collection<GroupOut> result = new ArrayList<>();
        for (Group group: groups){
            result.add(new GroupOut(group));
        }
        return result;
    }
}
