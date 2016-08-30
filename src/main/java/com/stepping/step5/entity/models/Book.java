package com.stepping.step5.entity.models;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "books")
public class Book implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_book", nullable = false)
    private Integer bookId;

    @Column(name = "Name_book")
    private String bookName;

    @Column(name = "Name_author")
    private String authorName;

    @Column(name = "Publ_year")
    private int publYear;

    @ManyToOne
    private Library library;

    @Column(name = "OnlyHere")
    private Boolean onlyHere;

    @ManyToOne
    private Student student;


    @Column(name = "DateFrom")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private DateTime dateFrom;

    @Column(name = "dateTo")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private DateTime dateTo;

    public Boolean getOnlyHere() {
        return onlyHere;
    }

    public DateTime getDateFrom() {
        return dateFrom;
    }

    public int getPublYear() {
        return publYear;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Library getLibrary() {
        return library;
    }

    public String getBookName() {
        return bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public DateTime getDateTo() {
        return dateTo;
    }

    public Student getStudent() {
        return student;
    }

    public void setDateFrom(DateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setPublYear(int publYear) {
        this.publYear = publYear;
    }

    public void setDateTo(DateTime dateTo) {
        this.dateTo = dateTo;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setOnlyHere(Boolean onlyHere) {
        this.onlyHere = onlyHere;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book(){}

    public Book(int id, String bookName, String authorName,
                int publYear, Boolean onlyHere,
                DateTime dateFrom, DateTime dateTo){
        this.bookName = bookName;
        this.authorName = authorName;
        this.publYear = publYear;
        this.onlyHere = onlyHere;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Book{" + "BookId=" + bookId +
                ", bookName=" + bookName +
                ", authorName=" + authorName +
                ", publyshingYear=" + publYear +
                ", library=" + library.getLibraryId()+
                ", onlyHere=" + onlyHere +
                ", owner=" + student.getStudentId() +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +'}';
    }
}
