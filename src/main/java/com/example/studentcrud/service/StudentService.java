package com.example.studentcrud.service;

import com.example.studentcrud.domain.Student;
import com.example.studentcrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> listAll() {
		return studentRepository.findAll();
	}

	public Student save(Student student) {
		studentRepository.save(student);
        return student;
    }

	public Student get(Long id) {
		return studentRepository.findById(id).orElse(null);
	}

	public void delete(Long id) {
		studentRepository.deleteById(id);
	}
}
