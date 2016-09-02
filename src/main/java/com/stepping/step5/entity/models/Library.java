package com.stepping.step5.entity.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;



@Entity
@Table(name = "libraries")
public class Library implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Id_library", nullable = false)
    private Integer libraryId;

    @ManyToOne
    @JoinColumn(name = "Id_univer")
    private University university;

    @OneToMany(mappedBy = "library")
    private List<Librarian> librarien;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

    public void deleteLibrarian(Librarian librarian){librarien.remove(librarian);}

    public void addLibrarian(Librarian librarian){
        librarien.add(librarian);
    }

    public void deleteBook(Book book){
        books.remove(book);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public int getNumberOfLibrarians(){
        return librarien.size();
    }

    public int getNumberOfBooks(){
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Librarian> getLibrarien() {
        return librarien;
    }

    public void setLibrarien(List<Librarian> librarien) {
        this.librarien = librarien;
    }

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

    public Library(University university, List<Librarian> librarien, List<Book> books){
        this.librarien = librarien;
        this.university = university;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" + "libraryId=" + libraryId +
                ", university=" + university.getUniverName() +
                ", number of librarians=" + getNumberOfLibrarians() +
                ", number of books=" + getNumberOfBooks() +'}';
    }
}