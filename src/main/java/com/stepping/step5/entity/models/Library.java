package com.stepping.step5.entity.models;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name = "libraries")
public class Library implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_library", nullable = false)
    private Integer libraryId;

    @ManyToOne
    private University university;

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Library(){}

    @Override
    public String toString() {
        return "Library{" + "libraryId=" + libraryId +
                ", university=" + university.getUniverName() +'}';
    }
}

