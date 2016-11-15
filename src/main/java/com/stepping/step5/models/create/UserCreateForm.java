package com.stepping.step5.models.create;

import com.stepping.step5.models.Role;
import com.stepping.step5.repository.RoleRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

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
    private String passwordRepeated = "";

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
}
