package com.example.studentcrud.domain;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentname;
    private String course;
    private int fee;

    @ManyToOne
    private Group group;


    public Student() {
    }

    public Group getGroup() {
        return group;
    }

    public Long getId() {
        return id;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getCourse() {
        return course;
    }

    public int getFee() {
        return fee;
    }

    public Student(Long id, String studentname, String course, int fee, Group group) {

        this.id = id;
        this.studentname = studentname;
        this.course = course;
        this.fee = fee;
        this.group = group;
    }
}