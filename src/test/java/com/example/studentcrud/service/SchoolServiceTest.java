package com.example.studentcrud.service;

import com.example.studentcrud.domain.School;
import com.example.studentcrud.repository.SchoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @InjectMocks
    private SchoolService schoolService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllSchools() {
        // Arrange
        List<School> schools = Arrays.asList(new School(), new School());
        when(schoolRepository.findAll()).thenReturn(schools);

        // Act
        List<School> result = schoolService.listAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveSchool() {
        // Arrange
        School school = new School();
        when(schoolRepository.save(school)).thenReturn(school);

        // Act
        schoolService.save(school);

        // Assert
        verify(schoolRepository, times(1)).save(school);
    }

    @Test
    public void testGetSchoolById() {
        // Arrange
        long schoolId = 1L;
        School school = new School();
        when(schoolRepository.findById(schoolId)).thenReturn(Optional.of(school));

        // Act
        School result = schoolService.get(schoolId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteSchool() {
        // Arrange
        long schoolId = 1L;

        // Act
        schoolService.delete(schoolId);

        // Assert
        verify(schoolRepository, times(1)).deleteById(schoolId);
    }
}
