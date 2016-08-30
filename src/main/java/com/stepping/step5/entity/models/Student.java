package com.stepping.step5.entity.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;



@Entity
@Table(name = "students")
public class Student implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_student", nullable = false)
    private Integer studentId;

    @Column(name = "SName")
    private String secondName;

    @Column(name = "username")
    private String username;

    @Column(name = "FName")
    private String firstName;

    @Column(name = "PoBatkovy")
    private String poBatkovy;

    @Column(name = "password")
    private String password;

    @Column(name = "Phone_numb")
    private Long phoneNumb;

    @ManyToOne
    private Group group;

    public Integer getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPoBatkovy() {
        return poBatkovy;
    }

    public String getUsername() {
        return username;
    }

    public void setPoBatkovy(String poBatkovy) {
        this.poBatkovy = poBatkovy;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(Long phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student(){}

    public Student(String firstName, String secondName, String poBatkovy,
                   String username, String password, Long phoneNumb){
        this.firstName = firstName;
        this.secondName = secondName;
        this.poBatkovy = poBatkovy;
        this.username = username;
        this.password = password;
        this.phoneNumb = phoneNumb;
    }

    @Override
    public String toString() {
        return "Student{" + "StudentId=" + studentId +
                ", group=" + group.getGroupName() +
                ", firstName=" + firstName +
                ", secondName=" + secondName +
                ", poBatkovy=" + poBatkovy +
                ", username=" + username +
                ", password=" + password +
                ", phoneNumber=" + phoneNumb +'}';
    }
}
