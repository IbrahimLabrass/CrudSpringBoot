package com.example.studentcrud.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String schoolName;
    private String Location ;



    @OneToMany(mappedBy = "school")
    private List<Group> groups;

    @OneToMany(mappedBy = "school")
    private List<Teacher> teachers;

    public School() {

    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getLocation() {
        return Location;
    }


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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
