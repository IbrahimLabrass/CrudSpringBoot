package com.example.studentcrud.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teacherName;
    private String subject;

    @ManyToMany
    private List<Group> groups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getTeacherName() {
        return teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    public Teacher() {
        this.groups = new ArrayList<>();
    }
    public Teacher(long id, String teacherName, String  subject, List<Group> groups) {

        this.id = id;
        this.teacherName = teacherName;
        this.subject = subject;
        this.groups = groups;


    }


}
