package com.example.studentcrud.controller;

import com.example.studentcrud.domain.Teacher;
import com.example.studentcrud.service.ExportService;
import com.example.studentcrud.service.TeacherService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @Mock
    private ExportService exportService;

    @InjectMocks
    private TeacherController teacherController;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTeachers() {
        // Arrange
        List<Teacher> teachers = Arrays.asList(new Teacher(), new Teacher());
        when(teacherService.listAll()).thenReturn(teachers);

        // Act
        ResponseEntity<List<Teacher>> result = teacherController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, result.getBody().size());
    }

    @Test
    public void testAddTeacher() {
        // Arrange
        Teacher teacher = new Teacher();
        when(teacherService.save(teacher)).thenReturn(teacher);

        // Act
        ResponseEntity<Teacher> result = teacherController.addTeacher(teacher);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetTeacherById() {
        // Arrange
        long teacherId = 1L;
        Teacher teacher = new Teacher();
        when(teacherService.get(teacherId)).thenReturn(teacher);

        // Act
        ResponseEntity<Teacher> result = teacherController.getTeacherById(teacherId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetTeacherById_NotFound() {
        // Arrange
        long teacherId = 1L;
        when(teacherService.get(teacherId)).thenReturn(null);

        // Act
        ResponseEntity<Teacher> result = teacherController.getTeacherById(teacherId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testDeleteTeacher() {
        // Arrange
        long teacherId = 1L;

        // Act
        ResponseEntity<Void> result = teacherController.deleteTeacher(teacherId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(teacherService, times(1)).delete(teacherId);
    }


}
