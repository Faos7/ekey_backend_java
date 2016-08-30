package com.stepping.step5.entity.models;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "groups")
public class Group implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_group", nullable = false)
    private Integer groupId;

    @Column(name = "Name_group")
    private String groupName;

    @ManyToOne
    private University university;

    @ManyToOne
    private Course course;

    public Integer getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Group(){}

    public Group(String groupName){
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" + "groupId=" + groupId +
                ", university=" + university.getUniverName() +
                ", course" + course.getCourseNumb() +
                ", groupName " + groupName +'}';
    }
}