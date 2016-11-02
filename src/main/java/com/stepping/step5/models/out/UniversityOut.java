package com.stepping.step5.models.out;

import com.stepping.step5.models.Faculty;
import com.stepping.step5.models.Library;
import com.stepping.step5.models.University;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by re5 on 02.11.16.
 */
public class UniversityOut {

    private String name;

    private List<LibraryOut> libraries = new ArrayList<>();

    private List<FacultyOut> faculties = new ArrayList<>();

    public UniversityOut(University university){
        this.name = university.getName();
        for (Faculty faculty: university.getFaculties()){
            faculties.add(new FacultyOut(faculty));
        }
        for (Library library: university.getLibraries()){
            libraries.add(new LibraryOut(library));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LibraryOut> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<LibraryOut> libraries) {
        this.libraries = libraries;
    }

    public List<FacultyOut> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<FacultyOut> faculties) {
        this.faculties = faculties;
    }
}
