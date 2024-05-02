package com.example.studentcrud.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String Description;



    @ManyToMany(mappedBy = "groups")
    private List<Teacher> teachers;



    @OneToMany(mappedBy = "group")
    private List<Student> students;


    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }



    public void setId(Long id) {
        this.id = id;
    }


    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public String getGroupName() {
        return groupName;
    }

    public  Group (){
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();


    }
    public Group(long id, String groupName, String Description, List<Student> students, List<Teacher> teachers) {
        this.id = id ;
        this.groupName= groupName;
        this.Description= Description;
        this.students= students;
        this.teachers = teachers;
    }

}
