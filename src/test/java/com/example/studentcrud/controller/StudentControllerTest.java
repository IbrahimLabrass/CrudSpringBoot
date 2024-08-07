package com.example.studentcrud.controller;

import com.example.studentcrud.domain.Student;
import com.example.studentcrud.service.ExportService;
import com.example.studentcrud.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @Mock
    private ExportService exportService;

    @InjectMocks
    private StudentController studentController;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStudents() {
        // Arrange
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentService.listAll()).thenReturn(students);

        // Act
        ResponseEntity<List<Student>> result = studentController.getAllStudents();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, result.getBody().size());
    }

    @Test
    public void testAddStudent() {
        // Arrange
        Student student = new Student();
        when(studentService.save(student)).thenReturn(student);

        // Act
        ResponseEntity<Student> result = studentController.addStudent(student);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetStudentById() {
        // Arrange
        long studentId = 1L;
        Student student = new Student();
        when(studentService.get(studentId)).thenReturn(student);

        // Act
        ResponseEntity<Student> result = studentController.getStudentById(studentId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetStudentById_NotFound() {
        // Arrange
        long studentId = 1L;
        when(studentService.get(studentId)).thenReturn(null);

        // Act
        ResponseEntity<Student> result = studentController.getStudentById(studentId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testDeleteStudent() {
        // Arrange
        long studentId = 1L;

        // Act
        ResponseEntity<Void> result = studentController.deleteStudent(studentId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(studentService, times(1)).delete(studentId);
    }

}
