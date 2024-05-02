package com.example.studentcrud.service.test;

import com.example.studentcrud.domain.School;
import com.example.studentcrud.repository.SchoolRepository;
import com.example.studentcrud.service.SchoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @InjectMocks
    private SchoolService schoolService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllSchools() {
        List<School> schools = Arrays.asList(
                new School(1L, "School A", "Location A"),
                new School(2L, "School B", "Location B")
        );
        when(schoolRepository.findAll()).thenReturn(schools);

        List<School> actualSchools = schoolService.listAll();

        assertEquals(2, actualSchools.size());
        assertEquals("School A", actualSchools.get(0).getSchoolName());
        assertEquals("Location A", actualSchools.get(0).getLocation());
        assertEquals("School B", actualSchools.get(1).getSchoolName());
        assertEquals("Location B", actualSchools.get(1).getLocation());
    }

    @Test
    public void testSaveSchool() {
        School school = new School(1L, "School A", "Location A");
        when(schoolRepository.save(school)).thenReturn(school);

        School savedSchool = schoolService.save(school);

        assertEquals("School A", savedSchool.getSchoolName());
        assertEquals("Location A", savedSchool.getLocation());
    }

    @Test
    public void testGetSchoolById() {
        School school = new School(1L, "School A", "Location A");
        when(schoolRepository.findById(1L)).thenReturn(Optional.of(school));

        School actualSchool = schoolService.get(1L);

        assertEquals("School A", actualSchool.getSchoolName());
        assertEquals("Location A", actualSchool.getLocation());
    }

    @Test
    public void testDeleteSchool() {
        schoolService.delete(1L);
        verify(schoolRepository, times(1)).deleteById(1L);
    }
}
