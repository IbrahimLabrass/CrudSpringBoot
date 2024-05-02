package com.example.studentcrud.controller;

import com.example.studentcrud.domain.Teacher;
import com.example.studentcrud.service.ExportService;
import com.example.studentcrud.service.TeacherService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ExportService exportService;


    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.listAll();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.save(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.get(id);
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/export/excel")
    public void exportTeachersToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=teachers.xlsx");

        List<Teacher> teachers = teacherService.listAll();
        OutputStream outputStream = response.getOutputStream();
        exportService.exportTeachersToExcel(teachers, outputStream);
        outputStream.close();
    }

    @GetMapping("/export/pdf")
    public void exportTeachersToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=teachers.pdf");

        List<Teacher> teachers = teacherService.listAll();
        OutputStream outputStream = response.getOutputStream();
        exportService.exportTeachersToPDF(teachers, outputStream);
        outputStream.close();
    }
}
