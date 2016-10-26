package com.stepping.step5.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "courses")
public class Course implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Id_course")
    private Integer courseId;

    @Column(name = "Course_numb")
    private Integer number;

    @OneToMany(mappedBy = "course")
    private List<Group> groups;

    public Course(){}

    public void addGroup(Group group){
        groups.add(group);
    }

    public void deleteGroup(Group group){
        groups.remove(group);
    }

    public int getNumderOfGroups(){
        return groups.size();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId +
                ", courseNumb=" + number +
                ", number of groups=" + getNumderOfGroups() +'}';
    }
}
