package com.stepping.step5.entity.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "groups")
public class Group implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Id_group", nullable = false)
    private Integer groupId;

    @Column(name = "Name_group")
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "Id_univer")
    private University university;


    @ManyToOne
    @JoinColumn(name = "Id_course")
    private Course course;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public int getNumberOfStudents(){
        return students.size();
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void deleteStudent(Student student){
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

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

    public Group(University university, Course course, String groupName, List<Student> students){
        this.course = course;
        this.groupName = groupName;
        this.university = university;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" + "groupId=" + groupId +
                ", university=" + university.getUniverName() +
                ", course" + course.getCourseNumb() +
                ", groupName " + groupName +
                ", number of students=" + getNumberOfStudents() +'}';
    }
}