package com.example.studentcrud.service.test;

import com.example.studentcrud.domain.Student;
import com.example.studentcrud.repository.StudentRepository;
import com.example.studentcrud.service.StudentService;
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

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllStudents() {
        List<Student> students = Arrays.asList(
                new Student(1L, "ibrahim", "dev web", 100),
                new Student(2L, "ali", "dev mobile", 120)
        );
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> actualStudents = studentService.listAll();

        assertEquals(2, actualStudents.size());
        assertEquals("ibrahim", actualStudents.get(0).getStudentname());
        assertEquals("dev web", actualStudents.get(0).getCourse());
        assertEquals(100, actualStudents.get(0).getFee());
        assertEquals("ali", actualStudents.get(1).getStudentname());
        assertEquals("dev mobile", actualStudents.get(1).getCourse());
        assertEquals(120, actualStudents.get(1).getFee());
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student(1L, "ibrahim", "ali", 100);
        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.save(student);

        assertEquals("ibrahim", savedStudent.getStudentname());
        assertEquals("ali", savedStudent.getCourse());
        assertEquals(100, savedStudent.getFee());
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student(1L, "ibrahim", "ali", 100);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student actualStudent = studentService.get(1L);

        assertEquals("ibrahim", actualStudent.getStudentname());
        assertEquals("ali", actualStudent.getCourse());
        assertEquals(100, actualStudent.getFee());
    }

    @Test
    public void testDeleteStudent() {
        studentService.delete(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}
