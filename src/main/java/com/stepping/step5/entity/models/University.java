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
@Table(name = "university")
public class University implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Id_univer", nullable = false)
    private Integer univerId;

    @Column(name = "Name_univer")
    private String univerName;

    @OneToMany(mappedBy = "university")
    private List<Library> libraries;

    @OneToMany(mappedBy = "university")
    private List<Group> groups;

    public University() {}

    public University(String univerName){
        this.univerName = univerName;
    }

    public University(String univerName, List<Library> libraries, List<Group> groups) {
        this.univerName = univerName;
        this.libraries = libraries;
        this.groups = groups;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void addLibrary(Library library){
        libraries.add(library);
    }

    public  void deleteLibrary(Library library){
        libraries.remove(library);
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public Integer getUniverId() {
        return univerId;
    }

    public void setUniverId(Integer univerId) {
        this.univerId = univerId;
    }

    public String getUniverName() {
        return univerName;
    }

    public void setUniverName(String univerName) {
        this.univerName = univerName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Univercity{" + "univerId=" + univerId + ", univerName=" + univerName + '}';
    }
}