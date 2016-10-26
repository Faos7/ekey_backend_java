package com.stepping.step5.models;

import com.stepping.step5.entity.Course;
import com.stepping.step5.entity.Group;
import com.stepping.step5.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by re5 on 26.10.16.
 */

public class CourseOut {


    private int number;

    private List<String> groups = new ArrayList<String>() {};

    public CourseOut(Course course) {
        this.number = course.getNumber();
        List<Group> gr;
        for (Group group:course.getGroups()) {
            groups.add(group.getName());
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
