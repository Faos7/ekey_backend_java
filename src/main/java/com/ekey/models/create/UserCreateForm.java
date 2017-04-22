package com.ekey.models.create;

import com.ekey.models.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author Faost
 * @version 1.0
 */

public class UserCreateForm {

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String secondName ="";

    @NotEmpty
    private String thirdName = "";

    @NotNull
    private Long phoneNumb = 0L;

    @NotNull
    private Long studentCardId;

    private int id;

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String numberRFID = "";

    @NotEmpty
    private String passwordRepeated = "";

    @NotEmpty
    private String university = "";

    @NotEmpty
    private String faculty = "";

    @NotEmpty
    private String group = "";

    @NotEmpty
    private String library = "";

    @NotNull
    private Role role = new Role("DEFAULT");

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Long getPhoneNumb() {
        return phoneNumb;
    }

    public void setPhoneNumb(Long phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public Long getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(Long studentCardId) {
        this.studentCardId = studentCardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNumberRFID() {
        return numberRFID;
    }

    public void setNumberRFID(String numberRFID) {
        this.numberRFID = numberRFID;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
}
