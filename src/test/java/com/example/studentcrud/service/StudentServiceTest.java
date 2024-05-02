package com.example.studentcrud.service;

import com.example.studentcrud.domain.Student;
import com.example.studentcrud.repository.StudentRepository;
import org.junit.Test;
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
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testListAllStudents() {
        // Arrange
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);

        // Act
        List<Student> result = studentService.listAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveStudent() {
        // Arrange
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        // Act
        studentService.save(student);

        // Assert
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testGetStudentById() {
        // Arrange
        long studentId = 1L;
        Student student = new Student();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Act
        Student result = studentService.get(studentId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteStudent() {
        // Arrange
        long studentId = 1L;

        // Act
        studentService.delete(studentId);

        // Assert
        verify(studentRepository, times(1)).deleteById(studentId);
    }
}
