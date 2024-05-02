package com.example.studentcrud.service.test;



import com.example.studentcrud.domain.Teacher;
import com.example.studentcrud.repository.TeacherRepository;
import com.example.studentcrud.service.TeacherService;
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

public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllTeachers() {
        List<Teacher> teachers = Arrays.asList(
                new Teacher(1L, "John Doe", "dev web"),
                new Teacher(2L, "Jane Smith", "dev mobile")
        );
        when(teacherRepository.findAll()).thenReturn(teachers);

        List<Teacher> actualTeachers = teacherService.listAll();

        assertEquals(2, actualTeachers.size());
        assertEquals("John Doe", actualTeachers.get(0).getTeacherName());
        assertEquals("dev web", actualTeachers.get(0).getSubject());
        assertEquals("Jane Smith", actualTeachers.get(1).getTeacherName());
        assertEquals("dev mobile", actualTeachers.get(1).getSubject());
    }

    @Test
    public void testSaveTeacher() {
        Teacher teacher = new Teacher(1L, "John Doe", "dev web");
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        Teacher savedTeacher = teacherService.save(teacher);

        assertEquals("John Doe", savedTeacher.getTeacherName());
        assertEquals("dev web", savedTeacher.getSubject());
    }

    @Test
    public void testGetTeacherById() {
        Teacher teacher = new Teacher(1L, "John Doe", "dev web");
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        Teacher actualTeacher = teacherService.get(1L);

        assertEquals("John Doe", actualTeacher.getTeacherName());
        assertEquals("dev web", actualTeacher.getSubject());
    }

    @Test
    public void testDeleteTeacher() {
        teacherService.delete(1L);
        verify(teacherRepository, times(1)).deleteById(1L);
    }
}
