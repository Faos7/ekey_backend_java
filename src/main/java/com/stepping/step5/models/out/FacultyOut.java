package com.stepping.step5.models.out;

import com.stepping.step5.models.Faculty;
import com.stepping.step5.models.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by re5 on 02.11.16.
 */
public class FacultyOut {

    private String name;

    private List<GroupOut> groups = new ArrayList<>();

    public FacultyOut(Faculty faculty){
        this.name = faculty.getName();
        for (Group group : faculty.getGroups()){
            groups.add(new GroupOut(group));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupOut> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupOut> groups) {
        this.groups = groups;
    }
}
