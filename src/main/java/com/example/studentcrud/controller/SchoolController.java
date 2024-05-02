package com.example.studentcrud.controller;

import com.example.studentcrud.domain.School;
import com.example.studentcrud.service.ExportService;
import com.example.studentcrud.service.SchoolService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private ExportService exportService;

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

    @GetMapping("/export/excel")
    public void exportSchoolsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=schools.xlsx");

        List<School> schools = schoolService.listAll();
        OutputStream outputStream = response.getOutputStream();
        exportService.exportSchoolsToExcel(schools, outputStream);
        outputStream.close();
    }

    @GetMapping("/export/pdf")
    public void exportSchoolsToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=schools.pdf");

        List<School> schools = schoolService.listAll();
        OutputStream outputStream = response.getOutputStream();
        exportService.exportSchoolsToPDF(schools, outputStream);
        outputStream.close();
    }
}
