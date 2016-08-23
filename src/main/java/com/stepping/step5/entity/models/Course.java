package com.stepping.step5.entity.models;

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
    private Integer courseNumb;

    @OneToMany(mappedBy = "course")
    private List<Group> groups;

    public Course(){}

    public Course(Integer courseNumb, List<Group> groups){
        this.courseNumb = courseNumb;
        this.groups = groups;
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

    public Integer getCourseNumb() {
        return courseNumb;
    }

    public void setCourseNumb(Integer courseNumb) {
        this.courseNumb = courseNumb;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", courseNumb=" + courseNumb + '}';
    }
}