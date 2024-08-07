package com.example.studentcrud.controller;

import com.example.studentcrud.domain.School;
import com.example.studentcrud.service.ExportService;
import com.example.studentcrud.service.SchoolService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class SchoolControllerTest {

    @Mock
    private SchoolService schoolService;

    @Mock
    private ExportService exportService;

    @InjectMocks
    private SchoolController schoolController;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSchools() {
        // Arrange
        List<School> schools = Arrays.asList(new School(), new School());
        when(schoolService.listAll()).thenReturn(schools);

        // Act
        ResponseEntity<List<School>> result = schoolController.getAllSchools();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, result.getBody().size());
    }

    @Test
    public void testAddSchool() {
        // Arrange
        School school = new School();
        when(schoolService.save(school)).thenReturn(school);

        // Act
        ResponseEntity<School> result = schoolController.addSchool(school);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetSchoolById() {
        // Arrange
        long schoolId = 1L;
        School school = new School();
        when(schoolService.get(schoolId)).thenReturn(school);

        // Act
        ResponseEntity<School> result = schoolController.getSchoolById(schoolId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetSchoolById_NotFound() {
        // Arrange
        long schoolId = 1L;
        when(schoolService.get(schoolId)).thenReturn(null);

        // Act
        ResponseEntity<School> result = schoolController.getSchoolById(schoolId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}
