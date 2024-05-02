package com.example.studentcrud.service;

import com.example.studentcrud.domain.Teacher;
import com.example.studentcrud.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @Test
    public void testListAllTeachers() {
        // Arrange
        List<Teacher> teachers = Arrays.asList(new Teacher(), new Teacher());
        when(teacherRepository.findAll()).thenReturn(teachers);

        // Act
        List<Teacher> result = teacherService.listAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveTeacher() {
        // Arrange
        Teacher teacher = new Teacher();
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // Act
        teacherService.save(teacher);

        // Assert
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    public void testGetTeacherById() {
        // Arrange
        long teacherId = 1L;
        Teacher teacher = new Teacher();
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        // Act
        Teacher result = teacherService.get(teacherId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteTeacher() {
        // Arrange
        long teacherId = 1L;

        // Act
        teacherService.delete(teacherId);

        // Assert
        verify(teacherRepository, times(1)).deleteById(teacherId);
    }
}
