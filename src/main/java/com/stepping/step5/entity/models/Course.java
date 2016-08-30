package com.stepping.step5.entity.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "courses")
public class Course implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_course")
    private Integer courseId;

    @Column(name = "Course_numb")
    private Integer courseNumb;

    public Course(){}

    public Course(Integer courseNumb){
        this.courseNumb = courseNumb;
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
        return "Course{" + "courseId=" + courseId +
                ", courseNumb=" + courseNumb +'}';
    }
}