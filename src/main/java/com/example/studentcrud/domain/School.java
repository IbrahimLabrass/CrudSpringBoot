package com.example.studentcrud.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String schoolName;

    public void setLocation(String location) {
        Location = location;
    }

    public String getLocation() {
        return Location;
    }

    private String Location ;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Long getId() {
        return id;
    }

    public String getSchoolName() {
        return schoolName;
    }
}
