package com.stepping.step5.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by faos7 on 12.11.16.
 */
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @SequenceGenerator(name="tx_sequence",sequenceName="tx_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tx_sequence")
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_book")
    private Book book;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_student")
    private User student;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_librarian")
    private User librarian;

    @JoinColumn(name = "dateFrom")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;

    @JoinColumn(name = "Date_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;

    @JoinColumn(name = "is_finished")
    private boolean isFinished;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
