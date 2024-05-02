package com.example.studentcrud.domain;


import jakarta.persistence.*;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String Description;



    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;


    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }


    public School getSchool() {
        return school;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }
    public Group(long id, String groupName, String Description) {
        this.id = id ;
        this.groupName= groupName;
        this.Description= Description;
    }

}
