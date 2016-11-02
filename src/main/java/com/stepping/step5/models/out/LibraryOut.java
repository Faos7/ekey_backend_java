package com.stepping.step5.models.out;

import com.stepping.step5.models.Library;

import javax.persistence.Column;

/**
 * Created by re5 on 02.11.16.
 */
public class LibraryOut {

    private Integer libraryId;

    private String name;

    public LibraryOut(Library library) {
        this.libraryId = library.getLibraryId();
        this.name = library.getName();
    }

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
