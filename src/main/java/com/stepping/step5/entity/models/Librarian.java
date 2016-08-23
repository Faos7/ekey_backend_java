package com.stepping.step5.entity.models;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name = "librarians")
public class Librarian implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Id_librarian", nullable = false)
    private Integer librarianId;

    @Column(name = "SName")
    private String secondName;

    @Column(name = "FName")
    private String firstName;

    @Column(name = "PoBatkyovy")
    private String poBatkyovy;

    @ManyToOne
    @JoinColumn(name = "Id_library")
    private Library library;

    public Librarian(){}

    public Librarian(Library library, String firstName, String secondName, String poBatkyovy){
        this.firstName = firstName;
        this.library = library;
        this.poBatkyovy = poBatkyovy;
        this.secondName = secondName;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Integer getLibrarianid() {
        return librarianId;
    }

    public void setLibrarianid(Integer librarianId) {
        this.librarianId = librarianId;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPoBatkyovy() {
        return poBatkyovy;
    }

    public void setPoBatkyovy(String poBatkyovy) {
        this.poBatkyovy = poBatkyovy;
    }

    @Override
    public String toString() {
        return "Librarian{" + "librarianId=" + librarianId +
                ", libraryId=" + library.getLibraryId() +
                ", FirstName=" + firstName +
                ", SecondName=" + secondName +
                ", PoBatkyovy= " + poBatkyovy + '}';
    }
}