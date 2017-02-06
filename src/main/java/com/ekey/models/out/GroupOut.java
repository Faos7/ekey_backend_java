package com.ekey.models.out;

import com.ekey.models.Group;


/**
 * Created by re5 on 02.11.16.
 */
public class GroupOut {

    private Integer groupId;

    private String name;


    public GroupOut(Group group){
        this.groupId = group.getGroupId();
        this.name = group.getName();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
