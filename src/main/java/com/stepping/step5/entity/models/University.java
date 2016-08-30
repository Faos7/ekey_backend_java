package com.stepping.step5.entity.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "university")
public class University implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_univer", nullable = false)
    private Integer univerId;



    @Column(name = "Name_univer")
    private String univerName;

    public University() {}

    public University(String univerName) {
        this.univerName = univerName;
    }

    public int getUniverId() {
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

    @Override
    public String toString() {
        return "Univercity{" + "univerId=" + univerId +
                ", univerName=" + univerName +'}';
    }
}