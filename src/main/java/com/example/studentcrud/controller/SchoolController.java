package com.example.studentcrud.controller;

import com.example.studentcrud.domain.School;
import com.example.studentcrud.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolService.listAll();
        return new ResponseEntity<>(schools, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<School> addSchool(@RequestBody School school) {
        School savedSchool = schoolService.save(school);
        return new ResponseEntity<>(savedSchool, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        School school = schoolService.get(id);
        if (school != null) {
            return new ResponseEntity<>(school, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
