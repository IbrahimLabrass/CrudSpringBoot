package com.example.studentcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentcrud.domain.Teacher;
import com.example.studentcrud.repository.TeacherRepository;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> listAll() {
        return teacherRepository.findAll();
    }

    public Teacher save(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    public Teacher get(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
