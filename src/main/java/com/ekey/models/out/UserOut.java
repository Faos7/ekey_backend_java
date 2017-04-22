package com.ekey.models.out;

import com.ekey.models.Book;
import com.ekey.models.User;

import java.util.List;

/**
 * Created by faos7 on 27.10.16.
 */
public class UserOut {

    private String firstName;

    private String secondName;

    private String thirdName;

    private Long phoneNumb;

    private String role;

    private String library = "null";

    private Long studentCardId = 0L;

    private String group = "null";

    private String faculty = "null";

    private String university = "null";

    private List<Book> books = null;



    public UserOut(User user) {
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.thirdName = user.getThirdName();
        this.phoneNumb = user.getPhoneNumb();
        this.role = user.getRole().getName();
        try{
            this.library = user.getLibrary().getName();}
        catch (Exception e){}
        try{
            this.studentCardId = user.getStudentCardId();
            this.group = user.getGroup().getName();
            this.faculty = user.getGroup().getFaculty().getName();
            this.university = user.getGroup().getFaculty().getUniversity().getName();
            this.books = user.getBooks();}
        catch (Exception e){}
    }

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

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Long getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(Long studentCardId) {
        this.studentCardId = studentCardId;
    }
}
